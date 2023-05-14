package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAO;
import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.fxmview.FxUserMV;
import com.yupay.lunatico.model.ModelView;
import com.yupay.lunatico.model.User;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

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
             var str = dao().listAll(em)
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
