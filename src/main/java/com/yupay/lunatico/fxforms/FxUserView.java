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

import com.yupay.lunatico.fxflows.FxChangePasswordFlow;
import com.yupay.lunatico.fxflows.FxEditFlow;
import com.yupay.lunatico.fxflows.FxInsertFlow;
import com.yupay.lunatico.fxflows.FxListAllFlow;
import com.yupay.lunatico.fxmview.FxUserMV;
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

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Master view for Fx User management.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUserView extends MasterView {
    /**
     * The data to show in the table view after filtering and sorting.
     */
    private final ObservableList<FxUserMV> data
            = FXCollections.observableArrayList();

    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableColumn<FxUserMV, Boolean> colActive;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableColumn<FxUserMV, String> colID;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableColumn<FxUserMV, String> colName;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableColumn<FxUserMV, Boolean> colRoleAdmin;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableColumn<FxUserMV, Boolean> colRoleReport;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableColumn<FxUserMV, Boolean> colRoleSuper;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableColumn<FxUserMV, Boolean> colRoleViewer;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TableView<FxUserMV> tblData;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private Stage top;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private TextField txtFilter;

    /**
     * FXML initialization.
     */
    @FXML
    void initialize() {
        new CellFactoryManager<FxUserMV>()
                .addCheckColumn(colActive,
                        colRoleAdmin, colRoleReport,
                        colRoleSuper, colRoleViewer)
                .provide();
        new ValueFactoryManager<FxUserMV>()
                .add(colActive, FxUserMV::activeProperty)
                .add(colID, FxUserMV::idProperty)
                .add(colName, FxUserMV::realNameProperty)
                .add(colRoleAdmin, FxUserMV::roleAdminProperty)
                .add(colRoleReport, FxUserMV::roleReporterProperty)
                .add(colRoleSuper, FxUserMV::roleAdminSysProperty)
                .add(colRoleViewer, FxUserMV::roleViewerProperty)
                .provide();

        new TextFilter<>(tblData)
                .withData(data)
                .withFilter(new UserFilter())
                .setup(txtFilter.textProperty());

        bindEmptySelection(tblData);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onCreateAction() {
        FxInsertFlow.user()
                .withOnSuccess(data::add)
                .insert(top);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onForcePasswordAction() {
        Optional.ofNullable(tblData.getSelectionModel().getSelectedItem())
                .ifPresent(FxChangePasswordFlow.force());
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onRefreshDBAction() {
        FxListAllFlow.user()
                .withBefore(data::clear)
                .withForEach(data::add)
                .withAfter(this::sortTable)
                .go();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onEditRowAction() {
        Optional
                .ofNullable(tblData.getSelectionModel().getSelectedItem())
                .ifPresent(FxEditFlow.user()
                        .withParent(top)
                        .withAfterSuccess(p -> EasyAlert
                                .editCompleted("Los datos del usuario "
                                        + p.getId() + " han sido actualizados satisfactoriamente.")
                                .run()));
    }

    /**
     * Resets the table sorting columns.
     */
    private void sortTable() {
        var order = List.of(colActive, colID);
        colActive.setSortType(TableColumn.SortType.DESCENDING);
        colID.setSortType(TableColumn.SortType.ASCENDING);
        tblData.getSortOrder().setAll(order);
    }

    @Override
    protected @NotNull Stage getTop() {
        return top;
    }


    /**
     * User filtering feature.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class UserFilter implements Callable<Predicate<FxUserMV>>,
            Predicate<FxUserMV> {
        /**
         * Pattern compiled each time filter changes.
         * The pattern is a starts with.
         */
        private Pattern ptn;

        @Override
        public Predicate<FxUserMV> call() {
            var txt = txtFilter.getText();
            if (txt == null || txt.isBlank()) {
                ptn = null;
                return x -> true;
            } else {
                ptn = Patterns.startsWithCI(txt.strip());
                return this;
            }
        }

        @Override
        public boolean test(FxUserMV x) {
            if (x == null) return false;
            return ptn.matcher(x.getId()).matches()
                    || ptn.matcher(x.getRealName()).matches();
        }
    }
}
