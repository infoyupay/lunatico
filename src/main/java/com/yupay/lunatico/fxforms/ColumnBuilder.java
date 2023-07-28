/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxforms;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Convenient builder to easily create table columns.
 *
 * @param <S> type erasure of the table column.
 * @param <T> type erausre of the table view.
 * @author InfoYupay SACS
 * @version 1.0
 */
public class ColumnBuilder<S, T> implements Supplier<TableColumn<S, T>> {
    /**
     * The value factory for given column.
     */
    private final @NotNull Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>>
            valueFactory;
    /**
     * Column title.
     */
    private String title;
    /**
     * Column's prefered width.
     */
    private int prefWidth;

    /**
     * Constructor with a custom value factory.
     *
     * @param valueFactory the custom value factory.
     */
    public ColumnBuilder(@NotNull Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> valueFactory) {
        this.valueFactory = valueFactory;
    }

    /**
     * Static factory to create a column for an object.
     *
     * @param getter the function to get an observable.
     * @param <S>    type erasure of table view model.
     * @param <T>    type erausre of table column values.
     * @return the new column builder.
     */
    @Contract(value = "_ -> new", pure = true)
    public static <S, T> @NotNull ColumnBuilder<S, T> forObject(
            @NotNull Function<S, ObservableValue<T>> getter) {
        return new ColumnBuilder<>(data -> {
            var val = data.getValue();
            return val == null
                    ? new SimpleObjectProperty<>(null, "fake")
                    : getter.apply(val);
        });
    }

    /**
     * Creates a custom builder for TableColumn&lt;S, Long&gt; columns.
     *
     * @param getter the function to get a long property.
     * @param <S>    type erasure of the table view model.
     * @return a new builder.
     */
    @Contract(value = "_ -> new", pure = true)
    public static <S> @NotNull ColumnBuilder<S, Long> forLong(
            @NotNull Function<S, LongProperty> getter) {
        return new ColumnBuilder<>(data -> {
            var val = data.getValue();
            return val == null
                    ? new SimpleObjectProperty<>(null, "fake")
                    : getter.apply(val).asObject();
        });
    }

    /**
     * Fluent setter - with.
     *
     * @param title new value to set in {@link #title}
     * @return this instance.
     */
    public final ColumnBuilder<S, T> withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #title}
     */
    public final String getTitle() {
        return title;
    }

    /**
     * Fluent setter - with.
     *
     * @param prefWidth new value to set in {@link #prefWidth}
     * @return this instance.
     */
    public final ColumnBuilder<S, T> withPrefWidth(int prefWidth) {
        this.prefWidth = prefWidth;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #prefWidth}
     */
    public final int getPrefWidth() {
        return prefWidth;
    }

    @Override
    public TableColumn<S, T> get() {
        var r = new TableColumn<S, T>(getTitle());
        r.setCellValueFactory(valueFactory);
        if (getPrefWidth() > 0) r.setPrefWidth(getPrefWidth());
        return r;
    }
}
