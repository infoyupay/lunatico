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
import com.yupay.lunatico.fxmview.*;
import com.yupay.lunatico.model.*;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * The flow to list all items (without filter) from database.
 *
 * @param <T> type erasure of entities.
 * @param <U> type erasure of model view.
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class FxListAllFlow<T, U extends ModelView<T, U>> {

    /**
     * Things to do before flow execution.
     */
    private Runnable before;
    /**
     * Things to do with each retrieved element.
     */
    private Consumer<U> forEach;
    /**
     * Things to do after query execution, regardless of exceptions.
     */
    private Runnable after;

    /**
     * Static factory to create a flow for users.
     *
     * @return a new flow.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxListAllFlow<User, FxUserMV> user() {
        return new FxListAllFlow<>() {
            @Override
            protected @NotNull FxUserMV toView(@NotNull User model) {
                return new FxUserMV(model);
            }

            @Override
            protected @NotNull DAO<User> dao() {
                return DAOFactory.user();
            }
        };
    }

    /**
     * Static factory to create a flow for users.
     *
     * @return a new flow.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxListAllFlow<Warehouse, FxWarehouseMV> warehouse() {
        return new FxListAllFlow<>() {
            @Override
            protected @NotNull FxWarehouseMV toView(@NotNull Warehouse model) {
                return new FxWarehouseMV(model);
            }

            @Override
            protected @NotNull DAO<Warehouse> dao() {
                return DAOFactory.warehouse();
            }
        };
    }

    /**
     * Static factory to create a flow for measurement units.
     *
     * @return a new flow.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxListAllFlow<Unit, FxUnitMV> unit() {
        return new FxListAllFlow<>() {
            @Override
            protected @NotNull FxUnitMV toView(@NotNull Unit model) {
                return new FxUnitMV(model);
            }

            @Override
            protected @NotNull DAO<Unit> dao() {
                return DAOFactory.unit();
            }
        };
    }

    /**
     * Static factory to create a flow for warehouse items.
     *
     * @return a new flow.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxListAllFlow<Item, FxItemMV> item() {
        return new FxListAllFlow<>() {
            @Override
            protected @NotNull FxItemMV toView(@NotNull Item model) {
                return new FxItemMV(model);
            }

            @Override
            protected @NotNull DAO<Item> dao() {
                return DAOFactory.item();
            }
        };
    }

    /**
     * Static factory to create a flow for folio type entities.
     *
     * @return a new flow.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxListAllFlow<TypeFolio, FxFolioTypeMV> folioType() {
        return new FxListAllFlow<>() {
            @Override
            protected @NotNull FxFolioTypeMV toView(@NotNull TypeFolio model) {
                return new FxFolioTypeMV(model);
            }

            @Override
            protected @NotNull DAO<TypeFolio> dao() {
                return DAOFactory.typeFolio();
            }
        };
    }

    /**
     * Static factory to create a flow for Person entities.
     *
     * @return a new flow.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull FxListAllFlow<Person, FxPersonMV> person() {
        return new FxListAllFlow<>() {
            @Override
            protected @NotNull FxPersonMV toView(@NotNull Person model) {
                return new FxPersonMV(model);
            }

            @Override
            protected @NotNull DAO<Person> dao() {
                return DAOFactory.person();
            }
        };
    }

    /**
     * Static factory to create a flow for Balance overviews at a warehouse.
     *
     * @param warehouse place at where the balance is requested.
     * @return a new flow.
     */
    @Contract(value = " _-> new", pure = true)
    public static @NotNull FxListAllFlow<OvBalance, FxOvBalanceMV> balanceOverview(
            @NotNull FxWarehouseMV warehouse) {
        return new FxListAllFlow<>() {
            @Override
            protected @NotNull FxOvBalanceMV toView(@NotNull OvBalance model) {
                return new FxOvBalanceMV(model);
            }

            @Override
            protected @NotNull DAO<OvBalance> dao() {
                return DAOFactory.balanceOverview();
            }

            @Override
            protected Stream<OvBalance> runQuery(@NotNull EntityManager em) {
                return DAOFactory.balanceOverview().listAtWarehouse(em, warehouse.toModel());
            }
        };
    }

    /**
     * Function to convert a model entity into a model view entity.
     *
     * @param model the model entity to convert.
     * @return a model view entity.
     */
    @Contract("_->new")
    protected abstract @NotNull U toView(@NotNull T model);

    /**
     * DAO implementation factory.
     *
     * @return the dao implementation for the entity.
     */
    protected abstract @NotNull DAO<T> dao();

    /**
     * Entry point, will run the JPA query.
     */
    public void go() {
        if (getBefore() != null) getBefore().run();
        try (var em = DataSource.em();
             var str = runQuery(em)
                     .peek(em::refresh)
                     .map(this::toView)) {

            if (getForEach() != null) str.forEach(getForEach());

        } catch (Exception e) {
            new ErrorHandler()
                    .withBriefing("Ocurrió un error al seleccionar todos " +
                            "los elementos de la base de datos.")
                    .accept(e);
        } finally {
            if (getAfter() != null) getAfter().run();
        }
    }

    /**
     * Runs the query in the DAO layer. This is
     * a protected method, so if a custom list flow
     * requires a custom query, it should be overwritten.
     *
     * @param em the entity manager object.
     * @return list all result.
     */
    protected Stream<T> runQuery(@NotNull EntityManager em) {
        return dao().listAll(em);
    }

    /**
     * Fluent setter - with.
     *
     * @param before new value to set in {@link #before}
     * @return this instance.
     */
    public final FxListAllFlow<T, U> withBefore(Runnable before) {
        this.before = before;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #before}
     */
    public final Runnable getBefore() {
        return before;
    }

    /**
     * Fluent setter - with.
     *
     * @param forEach new value to set in {@link #forEach}
     * @return this instance.
     */
    public final FxListAllFlow<T, U> withForEach(Consumer<U> forEach) {
        this.forEach = forEach;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #forEach}
     */
    public final Consumer<U> getForEach() {
        return forEach;
    }

    /**
     * Fluent setter - with.
     *
     * @param after new value to set in {@link #after}
     * @return this instance.
     */
    @Contract(value = "_ -> this")
    public final FxListAllFlow<T, U> withAfter(Runnable after) {
        this.after = after;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #after}
     */
    public final Runnable getAfter() {
        return after;
    }
}
