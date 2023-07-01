package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.EasyAlert;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxforms.FxMovementCard;
import com.yupay.lunatico.fxmview.FxMovementMV;
import com.yupay.lunatico.fxtools.EditorMode;
import com.yupay.lunatico.toolbox.MovementDetailEntry;
import com.yupay.lunatico.toolbox.MovementDetailProcessor;
import jakarta.persistence.EntityTransaction;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

/**
 * Custom flow to insert a movement entry.
 * It'll take care of stocks updates and validation.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxMoveFlow {
    /**
     * Lazily built card dialog form.
     */
    private FxMovementCard dialog;
    /**
     * The window (stage) where this dialog
     * should be shown.
     */
    private Stage owner;

    /**
     * Lazily builds the dialog and gets it.
     *
     * @return the built dialog.
     */
    private @NotNull FxMovementCard getDialog() {
        if (dialog == null) {
            dialog = FxForms.movementCard();
            if (owner != null) {
                dialog.initOwner(owner);
            }
            dialog.initModality(Modality.NONE);
            dialog.resultProperty().addListener((x, o, n) -> {
                if (n != null) accept(n);
            });
        }
        return dialog;
    }

    /**
     * Shows the creator dialog.
     */
    public void showCreator() {
        getDialog().setValue(getDialog().blank());
        getDialog().setFormMode(EditorMode.CREATOR);
        getDialog().show();
    }

    /**
     * Convenient method to send the model view element
     * into the persistence layer.
     *
     * @param t the user input.
     */
    private void accept(@NotNull FxMovementMV t) {
        //Convert to model.
        var mdl = t.toModel();

        EntityTransaction et = null;
        try (var em = DataSource.em()) {
            et = em.getTransaction();
            et.begin();
            var daoBalance = DAOFactory.balance();
            //Map stocks and lock item global balances.
            var stockMap = mdl.getDetail()
                    .stream()
                    .map(dt -> MovementDetailEntry.fetchBalanceForDetail(
                            dt,
                            mdl.getWarehouse(),
                            em,
                            daoBalance))
                    .toList();

            //If I have to substract
            if (mdl.getType().shouldSubtract()) {
                //First, check valid stocks.
                var invalidStocks = stockMap.stream()
                        .filter(MovementDetailEntry::canSubstract)
                        .map(MovementDetailEntry::toString)
                        .toList();
                //If any movement quantity exceeds avialable stock,
                if (!invalidStocks.isEmpty()) {
                    //It should be rejected throwing an exception.
                    throw new IllegalStateException(
                            invalidStocks.stream()
                                    .collect(Collectors.joining("\n",
                                            "INSUFFICIENT STOCK FOR THE FOLLOWING ITEMS:\n",
                                            "\nCheck available stock first.")));
                }
            }

            //If data is valid, perform.
            stockMap.forEach(new MovementDetailProcessor()
                    .andThen(entry -> {
                        em.merge(entry.item());
                        em.merge(entry.balance());
                    }));
            em.persist(mdl);

            //Commit
            et.commit();

            EasyAlert.info()
                    .withTitle("Operación Completada")
                    .withHeaderText("Se ha creado exitosamente el movimiento de "
                            + t.getType() + " en la base de datos.")
                    .withContentText("Se ha generado el número de transacción " + t.getId())
                    .buttonOkOnly()
                    .showAndWait();
        } catch (RuntimeException e) {
            DataSource.checkAndRollback(et);
            new ErrorHandler()
                    .withBriefing("Ocurrió un error al crear el movimiento de productos.")
                    .accept(e);
            getDialog().setResult(null);
            getDialog().show();
        }
    }

    /**
     * Fluent setter - with.
     *
     * @param owner new value to set in {@link #owner}
     * @return this instance.
     */
    public final FxMoveFlow withOwner(Stage owner) {
        this.owner = owner;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #owner}
     */
    public final Stage getOwner() {
        return owner;
    }
}
