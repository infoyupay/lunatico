package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAO;
import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxmview.FxUserMV;
import com.yupay.lunatico.fxmview.FxWarehouseMV;
import com.yupay.lunatico.fxtools.CardDialog;
import com.yupay.lunatico.model.ModelView;
import com.yupay.lunatico.model.User;
import com.yupay.lunatico.model.Warehouse;
import jakarta.persistence.EntityTransaction;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Work flow to insert one item into database.
 *
 * @param <T> type erasure of entity item.
 * @param <U> type erasure of model view.
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class FxInsertFlow<T, U extends ModelView<T, U>>
        implements Consumer<U> {

    /**
     * What to do on successful execution of the insert JPA query.
     */
    private Consumer<U> onSuccess;

    /**
     * Static factory to create a User.
     *
     * @return the flow to insert an item.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxInsertFlow<User, FxUserMV> user() {
        return new FxInsertFlow<>() {
            @Override
            protected @NotNull CardDialog<FxUserMV> card() {
                return FxForms.userCard();
            }

            @Override
            protected @NotNull DAO<User> dao() {
                return DAOFactory.user();
            }
        };
    }

    /**
     * Static factory to create a Warehouse.
     *
     * @return the flow ti insert an item.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxInsertFlow<Warehouse, FxWarehouseMV> warehouse() {
        return new FxInsertFlow<>() {
            @Override
            protected @NotNull CardDialog<FxWarehouseMV> card() {
                return FxForms.warehouseCard();
            }

            @Override
            protected @NotNull DAO<Warehouse> dao() {
                return DAOFactory.warehouse();
            }

        };
    }

    /**
     * Creates a form card dialog instance.
     *
     * @return the card dialog instance.
     */
    @Contract("->new")
    protected abstract @NotNull CardDialog<U> card();

    /**
     * Creates a DAO instance to perform the insert operation.
     *
     * @return the data access object instance.
     */
    @Contract("->new")
    protected abstract @NotNull DAO<T> dao();

    /**
     * Fluent setter - with.
     *
     * @param onSuccess new value to set in {@link #onSuccess}
     * @return this instance.
     */
    public final FxInsertFlow<T, U> withOnSuccess(Consumer<U> onSuccess) {
        this.onSuccess = onSuccess;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #onSuccess}
     */
    public final Consumer<U> getOnSuccess() {
        return onSuccess;
    }

    @Override
    public void accept(@NotNull U u) {
        EntityTransaction et = null;
        try (var em = DataSource.em()) {
            var mdl = u.toModel();
            et = em.getTransaction();
            et.begin();
            dao().insertOne(em, mdl);
            et.commit();

            if (getOnSuccess() != null) {
                var view = u.deepCopy();
                view.fromModel(mdl);
                getOnSuccess().accept(view);
            }
        } catch (RuntimeException e) {
            DataSource.checkAndRollback(et);
            new ErrorHandler()
                    .withBriefing(
                            "Ocurrió un error mientras intentabas insertar " +
                                    "un elemento nuevo a la base de datos.")
                    .accept(e);
        }
    }

    /**
     * Shows the proper dialog to the user and then
     * waits for user input to send an insert query.
     *
     * @param owner the stage parent for the dialogs.
     */
    public void insert(@Nullable Stage owner) {
        card().creator(owner)
                .ifPresent(this);
    }

}
