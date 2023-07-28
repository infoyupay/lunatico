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

import com.yupay.lunatico.fxflows.FxEditFlow;
import com.yupay.lunatico.fxflows.FxInsertFlow;
import com.yupay.lunatico.fxflows.FxListAllFlow;
import com.yupay.lunatico.fxmview.FxPersonMV;
import com.yupay.lunatico.fxtools.CellFactoryManager;
import com.yupay.lunatico.fxtools.Patterns;
import com.yupay.lunatico.fxtools.TextFilter;
import com.yupay.lunatico.fxtools.ValueFactoryManager;
import com.yupay.lunatico.model.DoiType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * FXML controller for Person master view.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxPersonView extends MasterView {

    /**
     * The underlying data to show in table view.
     */
    private final ObservableList<FxPersonMV> data
            = FXCollections.observableArrayList();

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private TableColumn<FxPersonMV, Boolean> colActive;

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private TableColumn<FxPersonMV, String> colDoiNum;

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private TableColumn<FxPersonMV, DoiType> colDoiType;

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private TableColumn<FxPersonMV, Long> colID;

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private TableColumn<FxPersonMV, String> colName;

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private TableView<FxPersonMV> tblData;

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private Stage top;

    /**
     * FXML control injected from person-view.fxml
     */
    @FXML
    private TextField txtFilter;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        bindEmptySelection(tblData);

        new CellFactoryManager<FxPersonMV>()
                .addCheckColumn(colActive)
                .provide();

        new ValueFactoryManager<FxPersonMV>()
                .addLong(colID, FxPersonMV::idProperty)
                .add(colActive, FxPersonMV::activeProperty)
                .add(colDoiNum, FxPersonMV::doiNumProperty)
                .add(colDoiType, FxPersonMV::doiTypeProperty)
                .add(colName, FxPersonMV::nameProperty)
                .provide();

        new TextFilter<>(tblData)
                .withData(data)
                .withFilter(new UserFiltering())
                .setup(txtFilter.textProperty());
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onAddRowAction() {
        FxInsertFlow.person()
                .withOnSuccess(data::add)
                .insert(top);
    }

    @FXML
    @Override
    void onEditRowAction() {
        Optional.ofNullable(tblData.getSelectionModel().getSelectedItem())
                .ifPresent(FxEditFlow.person()
                        .withParent(top)
                        .withAfterSuccess(p ->
                                EasyAlert.editCompleted("Se completó la edición de la persona "
                                                + p.getName())
                                        .run()));
    }

    @FXML
    @Override
    void onRefreshDBAction() {
        FxListAllFlow.person()
                .withBefore(data::clear)
                .withForEach(data::add)
                .withAfter(this::resetSorting)
                .go();
    }

    @Override
    protected @NotNull Stage getTop() {
        return top;
    }

    /**
     * Resets the table view sort order to the default by design.
     */
    private void resetSorting() {
        colActive.setSortType(TableColumn.SortType.DESCENDING);
        colName.setSortType(TableColumn.SortType.ASCENDING);
        colDoiNum.setSortType(TableColumn.SortType.ASCENDING);
        colID.setSortType(TableColumn.SortType.ASCENDING);
        tblData.getSortOrder().setAll(Arrays.asList(
                colActive, colName, colDoiNum, colID));
    }

    /**
     * User text filtering feature implementation.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class UserFiltering
            implements Callable<Predicate<FxPersonMV>>,
            Predicate<FxPersonMV> {
        /**
         * Holds the last pattern starts with
         * compiled from the filtering text.
         */
        Pattern ptnStarts,
        /**
         * Holds the last pattern contains
         * compiled from the filtering text.
         */
        ptnContains;

        @Override
        public Predicate<FxPersonMV> call() {
            var str = txtFilter.getText();
            if (str == null || str.isBlank()) {
                ptnStarts = null;
                ptnContains = null;
                return Objects::nonNull;
            } else {
                var striped = str.strip();
                ptnStarts = Patterns.startsWithCI(striped);
                ptnContains = Patterns.containsCI(striped);
                return this;
            }
        }

        @Override
        public boolean test(FxPersonMV x) {
            return x != null
                    && (assertDoi(x.getDoiNum())
                    || assertName(x.getName()));
        }

        /**
         * Checks that a doi number complies with
         * the captured filter pattern.
         *
         * @param doi the doi number.
         * @return true if doi passes test.
         */
        private boolean assertDoi(@Nullable String doi) {
            if (doi == null || doi.isBlank()) {
                return false;
            } else {
                return ptnStarts.matcher(doi).matches();
            }
        }

        /**
         * Checks that a name complies with
         * the captured filter pattern.
         *
         * @param name the name.
         * @return true if name passes test.
         */
        private boolean assertName(@Nullable String name) {
            if (name == null || name.isBlank()) {
                return false;
            } else {
                return ptnContains.matcher(name).matches();
            }
        }
    }
}
