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

import com.yupay.lunatico.fxtools.Patterns;
import com.yupay.lunatico.fxtools.TextFilter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Convenient Dialog to allow the user to choose
 * one or more items when a search result returns
 * more than one item.
 *
 * @param <T> type erasure of the item to choose.
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxSearchCard<T> extends Dialog<T> {
    /**
     * The underlying data to show in the view.
     */
    private final ObservableList<T> data
            = FXCollections.observableArrayList();

    /**
     * FXML control injected from search-card.fxml
     */
    @FXML
    private TableView<T> tblData;

    /**
     * FXML control injected from search-card.fxml
     */
    @FXML
    private DialogPane top;

    /**
     * FXML control injected from search-card.fxml
     */
    @FXML
    private TextField txtFilter;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setTitle("Seleccionador de Objetos");
        setDialogPane(top);
        setResultConverter(b -> b == ButtonType.OK ?
                tblData.getSelectionModel().getSelectedItem() : null);
        txtFilter.textProperty().addListener(o -> {
            if (!tblData.getItems().isEmpty()) {
                tblData.getSelectionModel().clearAndSelect(0);
            } else {
                tblData.getSelectionModel().clearSelection();
            }
        });
    }

    /**
     * FXML event handler.
     *
     * @param evt the event object.
     */
    @FXML
    void onTableClicked(@NotNull MouseEvent evt) {
        if (evt.isConsumed()) return;
        if (evt.getButton() == MouseButton.PRIMARY
                && evt.getClickCount() > 1) {
            var sel = tblData.getSelectionModel().getSelectedItem();
            if (sel != null) {
                setResult(sel);
            }
            evt.consume();
        }
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #data}
     */
    public ObservableList<T> getData() {
        return data;
    }

    /**
     * Adds a table column to the table view.
     *
     * @param column the column to add.
     * @param <U>    type erausre of column.
     * @return this instance.
     */
    @Contract("_->this")
    public @NotNull <U> FxSearchCard<T> addColumn(
            @NotNull TableColumn<T, U> column) {
        tblData.getColumns().add(column);
        return this;
    }

    /**
     * Sets up the filtering system.
     *
     * @param filter the text filter based in
     *               a Pattern constantly refreshed
     *               according to user input.
     * @return this instance.
     */
    public FxSearchCard<T> setupFilter(@NotNull BiPredicate<Pattern, T> filter) {
        new TextFilter<>(tblData)
                .withData(getData())
                .withFilter(new UserFilter(filter))
                .setup(txtFilter.textProperty());
        return this;
    }

    /**
     * Sets all the items to show in table view.
     *
     * @param data the data to show.
     * @return this instance.
     */
    @Contract("_->this")
    public @NotNull FxSearchCard<T> setupData(@NotNull List<T> data) {
        getData().setAll(data);
        return this;
    }

    /**
     * User filtering feature implementation.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class UserFilter implements Callable<Predicate<T>>,
            Predicate<T> {
        /**
         * The inner filter.
         */
        private final BiPredicate<Pattern, T> filter;
        /**
         * Holds the pattern to perform filtering.
         */
        private Pattern ptn;

        /**
         * Full constructor.
         *
         * @param filter the inner filter predicate.
         */
        public UserFilter(BiPredicate<Pattern, T> filter) {
            this.filter = filter;
        }

        @Override
        public Predicate<T> call() {
            var txt = txtFilter.getText();
            if (txt == null || txt.isBlank()) {
                ptn = null;
                return Objects::nonNull;
            } else {
                ptn = Patterns.containsCI(txt);
                return this;
            }
        }

        @Override
        public boolean test(T t) {
            return t != null && filter.test(ptn, t);
        }
    }

}
