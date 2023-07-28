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
import com.yupay.lunatico.fxmview.FxFolioTypeMV;
import com.yupay.lunatico.fxtools.CellFactoryManager;
import com.yupay.lunatico.fxtools.Patterns;
import com.yupay.lunatico.fxtools.TextFilter;
import com.yupay.lunatico.fxtools.ValueFactoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * JavaFX controller for Folio Type master view.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxFolioTypeView extends MasterView {
    /**
     * The underlying data to show in table view.
     */
    private final ObservableList<FxFolioTypeMV> data
            = FXCollections.observableArrayList();

    /**
     * FXML control injected from folio_type-view.fxml
     */
    @FXML
    private TableColumn<FxFolioTypeMV, Boolean> colActive;

    /**
     * FXML control injected from folio_type-view.fxml
     */
    @FXML
    private TableColumn<FxFolioTypeMV, Long> colID;

    /**
     * FXML control injected from folio_type-view.fxml
     */
    @FXML
    private TableColumn<FxFolioTypeMV, String> colName;

    /**
     * FXML control injected from folio_type-view.fxml
     */
    @FXML
    private TableView<FxFolioTypeMV> tblData;

    /**
     * FXML control injected from folio_type-view.fxml
     */
    @FXML
    private Stage top;

    /**
     * FXML control injected from folio_type-view.fxml
     */
    @FXML
    private TextField txtFilter;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        new CellFactoryManager<FxFolioTypeMV>()
                .addCheckColumn(colActive)
                .provide();
        new ValueFactoryManager<FxFolioTypeMV>()
                .addLong(colID, FxFolioTypeMV::idProperty)
                .add(colName, FxFolioTypeMV::nameProperty)
                .add(colActive, FxFolioTypeMV::activeProperty)
                .provide();
        new TextFilter<>(tblData)
                .withData(data)
                .withFilter(new UserFiltering())
                .setup(txtFilter.textProperty());

        bindEmptySelection(tblData);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onAddRowAction() {
        FxInsertFlow.typeFolio()
                .withOnSuccess(data::add)
                .insert(top);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onEditRowAction() {
        Optional.ofNullable(tblData.getSelectionModel().getSelectedItem())
                .ifPresent(FxEditFlow.typeFolio()
                        .withParent(top)
                        .withAfterSuccess(p -> EasyAlert.editCompleted(
                                        "Se completó la edición del tipo de folio "
                                                + p.getName()
                                ).run()
                        ));
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onRefreshDBAction() {
        FxListAllFlow.folioType()
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
     * Resets the default sorting order of the table view.
     */
    private void resetSorting() {
        colActive.setSortType(TableColumn.SortType.DESCENDING);
        colName.setSortType(TableColumn.SortType.ASCENDING);
        colID.setSortType(TableColumn.SortType.ASCENDING);
        tblData.getSortOrder().setAll(Arrays.asList(colActive, colName, colID));
    }

    /**
     * User filtering feature.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class UserFiltering implements
            Callable<Predicate<FxFolioTypeMV>>,
            Predicate<FxFolioTypeMV> {
        Pattern ptn;

        @Override
        public Predicate<FxFolioTypeMV> call() {
            var str = txtFilter.getText();
            if (str == null || str.isBlank()) {
                ptn = null;
                return Objects::nonNull;
            } else {
                ptn = Patterns.containsCI(str.strip());
                return this;
            }
        }

        @Override
        public boolean test(FxFolioTypeMV x) {
            return x != null
                    && x.getName() != null
                    && ptn.matcher(x.getName()).matches();
        }
    }
}
