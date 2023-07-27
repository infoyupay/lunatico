package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxEditFlow;
import com.yupay.lunatico.fxflows.FxInsertFlow;
import com.yupay.lunatico.fxflows.FxListAllFlow;
import com.yupay.lunatico.fxmview.FxWarehouseMV;
import com.yupay.lunatico.fxtools.CellFactoryManager;
import com.yupay.lunatico.fxtools.Patterns;
import com.yupay.lunatico.fxtools.TextFilter;
import com.yupay.lunatico.fxtools.ValueFactoryManager;
import com.yupay.lunatico.model.VirtualWarehouseType;
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
 * The warehouse master view.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxWarehouseView extends MasterView {
    /**
     * The data to show in the table view.
     */
    private final ObservableList<FxWarehouseMV> data
            = FXCollections.observableArrayList();

    /**
     * FXML control injected from warehouse-view.fxml
     */
    @FXML
    private TableColumn<FxWarehouseMV, Boolean> colActive;
    /**
     * FXML control injected from warehouse-view.fxml
     */
    @FXML
    private TableColumn<FxWarehouseMV, Long> colID;
    /**
     * FXML control injected from warehouse-view.fxml
     */
    @FXML
    private TableColumn<FxWarehouseMV, String> colName;
    /**
     * FXML control injected from warehouse-view.fxml
     */
    @FXML
    private TableColumn<FxWarehouseMV, VirtualWarehouseType> colVirtualType;
    /**
     * FXML control injected from warehouse-view.fxml
     */
    @FXML
    private TableView<FxWarehouseMV> tblData;
    /**
     * FXML control injected from warehouse-view.fxml
     */
    @FXML
    private Stage top;
    /**
     * FXML control injected from warehouse-view.fxml
     */
    @FXML
    private TextField txtFilter;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        new CellFactoryManager<FxWarehouseMV>()
                .addCheckColumn(colActive)
                .provide();

        new ValueFactoryManager<FxWarehouseMV>()
                .add(colActive, FxWarehouseMV::activeProperty)
                .addLong(colID, FxWarehouseMV::idProperty)
                .add(colName, FxWarehouseMV::nameProperty)
                .add(colVirtualType, FxWarehouseMV::virtualTypeProperty)
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
    void onRefreshDBAction() {
        FxListAllFlow.warehouse()
                .withBefore(data::clear)
                .withAfter(this::resetSorting)
                .withForEach(data::add)
                .go();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onAddRowAction() {
        FxInsertFlow.warehouse().withOnSuccess(data::add).insert(top);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onEditRowAction() {
        Optional.ofNullable(tblData.getSelectionModel().getSelectedItem())
                .ifPresent(FxEditFlow.warehouse()
                        .withParent(top)
                        .withAfterSuccess(p -> EasyAlert
                                .editCompleted("Los datos del almacén "
                                        + p.getName() + " han sido actualizados satisfactoriamente.")
                                .run()));
    }

    /**
     * Convenient method to reset the sorting of the table columns.
     */
    private void resetSorting() {
        colActive.setSortType(TableColumn.SortType.DESCENDING);
        colVirtualType.setSortType(TableColumn.SortType.ASCENDING);
        colName.setSortType(TableColumn.SortType.ASCENDING);

        tblData.getSortOrder()
                .setAll(Arrays.asList(colActive, colVirtualType, colName));
    }

    @Override
    protected @NotNull Stage getTop() {
        return top;
    }

    /**
     * The filtering feature implementation.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class UserFiltering implements Callable<Predicate<FxWarehouseMV>>,
            Predicate<FxWarehouseMV> {
        /**
         * The pattern matching, compiled as contains, case insensitive.
         *
         * @see Patterns#containsCI(String)
         */
        private Pattern ptn;

        @Override
        public Predicate<FxWarehouseMV> call() {
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
        public boolean test(FxWarehouseMV x) {
            if (ptn == null) return x != null;
            return ptn.matcher(x.getName()).matches();
        }
    }
}
