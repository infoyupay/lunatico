package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAO;
import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxmview.*;
import com.yupay.lunatico.fxtools.CardDialog;
import com.yupay.lunatico.model.*;
import jakarta.persistence.EntityTransaction;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * A flow to edit an existing item.
 * If the edit succeeds, the given item will be updated,
 * afterwards the user may choose to perform an action after
 * successful completion of the flow.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class FxEditFlow<T, U extends ModelView<T, U>>
        implements Consumer<U> {

    /**
     * The parent (owner) stage to show the dialog.
     */
    private Stage parent;
    /**
     * What to do after successful update,
     * ny default does nothing.
     */
    private Consumer<U> afterSuccess = x -> {
    };

    /**
     * Static factory to create a new instance to edit users.
     *
     * @return the new instance.
     */
    @Contract(" -> new")
    public static @NotNull FxEditFlow<User, FxUserMV> user() {
        return new FxEditFlow<>() {
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
     * Static factory to create a new instance to edit warehouses.
     *
     * @return the new instance.
     */
    @Contract(" -> new")
    public static @NotNull FxEditFlow<Warehouse, FxWarehouseMV> warehouse() {
        return new FxEditFlow<>() {
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
     * Static factory to create a new instance to edit measurement units.
     *
     * @return the new instance.
     */
    @Contract(" -> new")
    public static @NotNull FxEditFlow<Unit, FxUnitMV> unit() {
        return new FxEditFlow<>() {
            @Override
            protected @NotNull CardDialog<FxUnitMV> card() {
                return FxForms.unitCard();
            }

            @Override
            protected @NotNull DAO<Unit> dao() {
                return DAOFactory.unit();
            }
        };
    }

    /**
     * Static factory to create a new instance to edit Item entities.
     *
     * @return the new instance.
     */
    @Contract(" -> new")
    public static @NotNull FxEditFlow<Item, FxItemMV> item() {
        return new FxEditFlow<>() {
            @Override
            protected @NotNull CardDialog<FxItemMV> card() {
                return FxForms.itemCard();
            }

            @Override
            protected @NotNull DAO<Item> dao() {
                return DAOFactory.item();
            }
        };
    }

    /**
     * Static factory to create a new instance to edit Type of folio entities.
     *
     * @return the new instance.
     */
    @Contract(" -> new")
    public static @NotNull FxEditFlow<TypeFolio, FxFolioTypeMV> typeFolio() {
        return new FxEditFlow<>() {
            @Override
            protected @NotNull CardDialog<FxFolioTypeMV> card() {
                return FxForms.folioTypeCard();
            }

            @Override
            protected @NotNull DAO<TypeFolio> dao() {
                return DAOFactory.typeFolio();
            }
        };
    }

    /**
     * Static factory to create a new instance to edit Person entities.
     *
     * @return the new instance.
     */
    @Contract(" -> new")
    public static @NotNull FxEditFlow<Person, FxPersonMV> person() {
        return new FxEditFlow<>() {
            @Override
            protected @NotNull CardDialog<FxPersonMV> card() {
                return FxForms.personCard();
            }

            @Override
            protected @NotNull DAO<Person> dao() {
                return DAOFactory.person();
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
     * Creates a DAO instance to perform the update operation.
     *
     * @return the data access object instance.
     */
    @Contract("->new")
    protected abstract @NotNull DAO<T> dao();

    @Override
    public void accept(@NotNull U u) {
        card().editor(getParent(), u.deepCopy())
                .map(ModelView::toModel)
                .map(this::update)
                .map(t -> {
                    u.fromModel(t);
                    return u;
                }).ifPresent(getAfterSuccess());
    }

    /**
     * Updates in the DAO layer the element.
     *
     * @param item the element to be updated.
     * @return the updated element.
     */
    private @NotNull T update(@NotNull T item) {
        EntityTransaction trx = null;
        T r;
        try (var em = DataSource.em()) {
            trx = em.getTransaction();
            trx.begin();
            r = dao().updateOne(em, item);
            trx.commit();
        } catch (RuntimeException e) {
            DataSource.checkAndRollback(trx);
            throw e;
        }
        return r;
    }

    /**
     * Fluent setter - with.
     *
     * @param parent new value to set in {@link #parent}
     * @return this instance.
     */
    public final FxEditFlow<T, U> withParent(Stage parent) {
        this.parent = parent;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #parent}
     */
    public final Stage getParent() {
        return parent;
    }

    /**
     * Fluent setter - with.
     *
     * @param afterSuccess new value to set in {@link #afterSuccess}
     * @return this instance.
     */
    public final FxEditFlow<T, U> withAfterSuccess(Consumer<U> afterSuccess) {
        this.afterSuccess = afterSuccess;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #afterSuccess}
     */
    public final Consumer<U> getAfterSuccess() {
        return afterSuccess;
    }
}
