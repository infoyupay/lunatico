package com.yupay.lunatico.fxtools;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;

import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * Workaround for table view text filtering.
 *
 * @param <S> type erasure of javaFX bean.
 * @author InfoYupay SACS
 * @version 1.0
 */
public class TextFilter<S> {
    /**
     * The view control.
     */
    private final TableView<S> view;

    /**
     * Data source.
     */
    private ObservableList<S> data;
    /**
     * Filter source.
     */
    private Callable<Predicate<S>> filter;

    /**
     * Default constructor.
     *
     * @param view the table view.
     */
    public TextFilter(TableView<S> view) {
        this.view = view;
    }

    /**
     * Fluent setter - with.
     *
     * @param data new value to set in {@link #data}
     * @return this instance.
     */
    public final TextFilter<S> withData(ObservableList<S> data) {
        this.data = data;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #data}
     */
    public final ObservableList<S> getData() {
        return data;
    }

    /**
     * Fluent setter - with.
     *
     * @param filter new value to set in {@link #filter}
     * @return this instance.
     */
    public final TextFilter<S> withFilter(Callable<Predicate<S>> filter) {
        this.filter = filter;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #filter}
     */
    public final Callable<Predicate<S>> getFilter() {
        return filter;
    }

    /**
     * Sets up the text filtering.
     *
     * @param inputs the dependencies / inputs of filter.
     */
    public void setup(Observable... inputs) {
        var filtered = new FilteredList<>(getData());
        filtered.predicateProperty().bind(
                Bindings.createObjectBinding(getFilter(), inputs));
        var sorted = new SortedList<>(filtered);
        sorted.comparatorProperty().bind(view.comparatorProperty());
        view.setItems(sorted);
    }

}
