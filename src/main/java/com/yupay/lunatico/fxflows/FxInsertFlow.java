/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAO;
import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxmview.*;
import com.yupay.lunatico.fxtools.CardDialog;
import com.yupay.lunatico.model.*;
import jakarta.persistence.EntityTransaction;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

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
     * Static factory to create a Measurement Unit.
     *
     * @return the flow to insert an item.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxInsertFlow<Unit, FxUnitMV> unit() {
        return new FxInsertFlow<>() {
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
     * Static factory to create a warehouse item.
     *
     * @return the flow to insert an item.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxInsertFlow<Item, FxItemMV> item() {
        return new FxInsertFlow<>() {
            @Override
            protected @NotNull CardDialog<FxItemMV> card() {
                return FxForms.itemCard();
            }

            @Contract(value = " -> new", pure = true)
            @Override
            protected @NotNull @Unmodifiable DAO<Item> dao() {
                return DAOFactory.item();
            }

            @Override
            public void accept(@NotNull FxItemMV u) {
                EntityTransaction et = null;
                try (var em = DataSource.em()) {
                    var mdl = u.toModel();
                    et = em.getTransaction();
                    et.begin();

                    //Standard insertion.
                    dao().insertOne(em, mdl);

                    /*#######################################################################################
                     # WARNING! DON'T TOUCH THIS CODE IF YOU DON'T UNDERSTAND WHY IT'S HERE.                #
                     # PLEASE READ ME FIRST!!! READ ME FIRST!!! READ MI FIRST!!! IT'S AN ORDER              #
                     # Let me explain why it's here. When any given item is created, it's provided          #
                     # with a historical balance for each existing warehouse, since after every             #
                     # move (a sale, a purchase, etc) such historical balance should be accumulated.        #
                     # Since we have plenty room to store even balances with amount 0, we prioritize        #
                     # eficiency at the moment of the transaction. This means that when an item is          #
                     # created, all posible historical balances will be also created, even if afterwards    #
                     # said item won't have movements at a given warehouse. This allows us to simply        #
                     # fetch and modify the historical balance without having to check the existence of one #
                     # and creating one if none exists. That's why the system would be inconsistent if      #
                     # the following two lines are removed.                                                 #
                     #######################################################################################*/

                    //Populate HISTORICAL balances
                    var daoBalance = DAOFactory.balance();
                    EntitiesFactory
                            .historicalBalances(mdl, DAOFactory.warehouse().listAll(em))
                            .forEach(b -> daoBalance.insertOne(em, b));

                    //Commit transaction.
                    et.commit();

                    if (getOnSuccess() != null) {
                        getOnSuccess().accept(new FxItemMV(mdl));
                    }
                } catch (RuntimeException e) {
                    DataSource.checkAndRollback(et);
                    new ErrorHandler()
                            .withBriefing(
                                    "Ocurrió un error mientras intentabas insertar " +
                                            "un nuevo artículo a la base de datos.")
                            .accept(e);
                }
            }
        };
    }

    /**
     * Static factory to create a Type of folio.
     *
     * @return the flow to insert an item.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxInsertFlow<TypeFolio, FxFolioTypeMV> typeFolio() {
        return new FxInsertFlow<>() {
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

            @Contract(value = " -> new", pure = true)
            @Override
            protected @NotNull @Unmodifiable DAO<Warehouse> dao() {
                return DAOFactory.warehouse();
            }

            @Override
            public void accept(@NotNull FxWarehouseMV u) {
                EntityTransaction et = null;
                try (var em = DataSource.em()) {
                    var mdl = u.toModel();
                    et = em.getTransaction();
                    et.begin();

                    //Standard insertion.
                    dao().insertOne(em, mdl);

                    /*#######################################################################################
                     # WARNING! DON'T TOUCH THIS CODE IF YOU DON'T UNDERSTAND WHY IT'S HERE.                #
                     # PLEASE READ ME FIRST!!! READ ME FIRST!!! READ MI FIRST!!! IT'S AN ORDER              #
                     # Let me explain why it's here. When any given item is created, it's provided          #
                     # with a historical balance for each existing warehouse, since after every             #
                     # move (a sale, a purchase, etc) such historical balance should be accumulated.        #
                     # Since we have plenty room to store even balances with amount 0, we prioritize        #
                     # eficiency at the moment of the transaction. This means that when an item is          #
                     # created, all posible historical balances will be also created, even if afterwards    #
                     # said item won't have movements at a given warehouse. This allows us to simply        #
                     # fetch and modify the historical balance without having to check the existence of one #
                     # and creating one if none exists. That's why the system would be inconsistent if      #
                     # the following two lines are removed.                                                 #
                     # Imagine you create a new warehouse after having created some items, then said old    #
                     # items would remain inconsistent of no historical balance entries were created.       #
                     #######################################################################################*/
                    //Populate HISTORICAL balances for each available item.
                    var daoBalance = DAOFactory.balance();
                    EntitiesFactory
                            .historicalBalancesForWarehouse(DAOFactory.item().listAll(em), mdl)
                            .forEach(b -> daoBalance.insertOne(em, b));

                    //Commit transaction.
                    et.commit();

                    if (getOnSuccess() != null) {
                        getOnSuccess().accept(new FxWarehouseMV(mdl));
                    }
                } catch (RuntimeException e) {
                    DataSource.checkAndRollback(et);
                    new ErrorHandler()
                            .withBriefing(
                                    "Ocurrió un error mientras intentabas insertar " +
                                            "un nuevo artículo a la base de datos.")
                            .accept(e);
                }
            }
        };
    }

    /**
     * Static factory to create a Person.
     *
     * @return the flow ti insert an item.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxInsertFlow<Person, FxPersonMV> person() {
        return new FxInsertFlow<>() {
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
