package com.yupay.lunatico.fxtools;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * A tool to easily provide TableCell factories.
 *
 * @param <S> Type erasure of javafx bean.
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class CellFactoryManager<S> {
    /**
     * Intermediate collection to store columns and factories pairs.
     */
    private final ArrayList<ColumnPair<S, ?>> columns
            = new ArrayList<>();

    /**
     * Adds a pair for a boolean column and a checkbox table cell.
     *
     * @param column the column(s) to pair.
     * @return this instance, never null.
     */
    @SafeVarargs
    @Contract("_->this")
    public final @NotNull CellFactoryManager<S> addCheckColumn(@NotNull TableColumn<S, Boolean>... column) {
        Stream.of(column)
                .map(c -> new ColumnPair<>(c, CheckBoxTableCell.forTableColumn(c)))
                .forEach(columns::add);
        return this;
    }

    /**
     * Invokes setCellFactory on each paired column.
     */
    public void provide() {
        columns.forEach(ColumnPair::provide);
    }

    /**
     * Inner record to hold column and factory information.
     *
     * @param column  the table column.
     * @param factory the factory information.
     * @param <S>     type erasure of table view.
     * @param <T>     type erasure of table column.
     */
    private record ColumnPair<S, T>(
            TableColumn<S, T> column,
            Callback<TableColumn<S, T>, TableCell<S, T>> factory
    ) {
        /**
         * Provides the paired cell factory.
         */
        void provide() {
            column.setCellFactory(factory);
        }
    }
}
