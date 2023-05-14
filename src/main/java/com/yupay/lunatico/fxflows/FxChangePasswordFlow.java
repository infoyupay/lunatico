package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.EasyAlert;
import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxmview.FxUserMV;
import com.yupay.lunatico.security.PasswordChangeRequest;
import jakarta.persistence.EntityTransaction;
import javafx.stage.Modality;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * This is a flow to change the password for a given user.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxChangePasswordFlow implements Consumer<FxUserMV> {

    /**
     * The type of password change.
     */
    private final ChangeType type;

    /**
     * Full constructor.
     *
     * @param type the type of password change.
     */
    private FxChangePasswordFlow(@NotNull ChangeType type) {
        this.type = type;
    }

    /**
     * Static factory to create a new flow to force the password change.
     *
     * @return a new flow, never null.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxChangePasswordFlow force() {
        return new FxChangePasswordFlow(ChangeType.FORCE);
    }

    /**
     * Static factory to create a new flow to allow a user to change
     * its own password.
     *
     * @return a new flow, never null.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxChangePasswordFlow self() {
        return new FxChangePasswordFlow(ChangeType.SELF);
    }

    @Override
    public void accept(FxUserMV fxUserMV) {
        if (fxUserMV == null) return;
        showDialog(fxUserMV.getId()).ifPresent(this::runQuery);
    }

    /**
     * Shows the dialog to change password.
     * The user will be requested some inputs
     * and based on them, a password change request
     * will be retrieved.
     *
     * @return the output result.
     */
    private Optional<PasswordChangeRequest> showDialog(@NotNull String user) {
        var r = FxForms.passwordChange();
        r.setRequireOldPassword(type == ChangeType.SELF);
        r.initModality(Modality.APPLICATION_MODAL);
        return r.showAndWait().map(rq -> rq.withUser(user));
    }

    /**
     * Running the query to perform operation.
     *
     * @param request the request.
     */
    private void runQuery(PasswordChangeRequest request) {
        //Create a variable to hold transaction.
        EntityTransaction trx = null;
        //Try with resources for the entity manager.
        try (var em = DataSource.em()) {
            //Get the transaction.
            trx = em.getTransaction();
            //Begin transaction.
            trx.begin();
            //Updated count.
            var cnt = 0;
            //If the request has no old possword, must be a force.
            if (type == ChangeType.FORCE) {
                //Forcing password.
                cnt = DAOFactory.user()
                        .forcePassword(em,
                                request.getUser(),
                                request.getNewPassword());
            }
            //If the request has an old password, must be an update.
            else {
                cnt = DAOFactory.user()
                        .updatePassword(em,
                                request.getUser(),
                                request.getOldPassword(),
                                request.getNewPassword());
            }
            //If result count is 0, no user was matched.
            if (cnt == 0) {
                throw new RuntimeException("The user " + request.getUser() +
                        " didn't match any registered user.");
            }//Commit transaction, only if there are updated rows.
            else {
                trx.commit();
                EasyAlert.info()
                        .withTitle("Operación Exitosa")
                        .withHeaderText("La operación de cambio de contraseña se ha realizado.")
                        .withContentText("Se ha alterado la contraseña del usuario " + request.getUser())
                        .buttonOkOnly()
                        .run();
            }
        } catch (RuntimeException e) {
            //Rollback transaction if a runtime exception is caught.
            DataSource.checkAndRollback(trx);
            //Throw the exception anyway.
            throw e;
        }
    }

    /**
     * The type of required change.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private enum ChangeType {
        /**
         * Self-change requires user authentication.
         */
        SELF,
        /**
         * Forced change requires no user authentication
         * provided the requesting user is a sys admin.
         */
        FORCE
    }
}
