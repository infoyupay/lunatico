package com.yupay.lunatico.fxtools;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Workaround to simplify the table cells
 * value factories mechanism. This will
 * hold a pair of column and factory which
 * will wrap a function to access javafx
 * properties directly from bean instead
 * of cell data features. It's safe because
 * a null row value will return a null accessor.
 *
 * @param <S> type erasure of javaFx bean.
 * @author InfoYupay SACS
 * @version 1.0
 */
public class ValueFactoryManager<S> {
    /**
     * Holds the pairs of columns and factories.
     */
    private final ArrayList<ValuePair<S, ?>> pairs
            = new ArrayList<>();

    /**
     * Adds a generic column - factory where the factory
     * will invoke an accessor function. It usually is
     * {@code S::xxxProperty}
     *
     * @param column  the table column.
     * @param accesor the accessor function.
     * @param <T>     type erasure of table column.
     * @return this instance.
     */
    @Contract("_,_->this")
    public <T>
    @NotNull ValueFactoryManager<S> add(
            TableColumn<S, T> column,
            Function<S, ObservableValue<T>> accesor
    ) {
        pairs.add(new ValuePair<>(column,
                d -> {
                    var v = d.getValue();
                    if (v == null) return null;
                    else return accesor.apply(v);
                }));
        return this;
    }

    /**
     * Sets cell value factories on each held column.
     */
    public void provide() {
        pairs.forEach(ValuePair::provide);
    }

    /**
     * Inner record to hold columns and factories.
     *
     * @param column  the table column.
     * @param factory the table cell value factory.
     * @param <S>     type erasure of table view.
     * @param <T>     type erasure of table column.
     * @author InfoYupay SACS
     * @version 1.0
     */
    private record ValuePair<S, T>(
            TableColumn<S, T> column,
            Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> factory
    ) {
        void provide() {
            column.setCellValueFactory(factory);
        }
    }
}