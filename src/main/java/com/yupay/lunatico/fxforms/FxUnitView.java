package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxEditFlow;
import com.yupay.lunatico.fxflows.FxInsertFlow;
import com.yupay.lunatico.fxflows.FxListAllFlow;
import com.yupay.lunatico.fxmview.FxUnitMV;
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
 * FXML controller for master view of Measurement units.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUnitView extends MasterView {
    /**
     * The underlying data to show in the table view.
     */
    private final ObservableList<FxUnitMV> data
            = FXCollections.observableArrayList();
    /**
     * FXML control injected from unit-view.fxml
     */
    @FXML
    private Stage top;
    /**
     * FXML control injected from unit-view.fxml
     */
    @FXML
    private TextField txtFilter;
    /**
     * FXML control injected from unit-view.fxml
     */
    @FXML
    private TableView<FxUnitMV> tblData;
    /**
     * FXML control injected from unit-view.fxml
     */
    @FXML
    private TableColumn<FxUnitMV, Long> colID;
    /**
     * FXML control injected from unit-view.fxml
     */
    @FXML
    private TableColumn<FxUnitMV, String> colTag;
    /**
     * FXML control injected from unit-view.fxml
     */
    @FXML
    private TableColumn<FxUnitMV, String> colSymbol;
    /**
     * FXML control injected from unit-view.fxml
     */
    @FXML
    private TableColumn<FxUnitMV, Boolean> colActive;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        bindEmptySelection(tblData);
        new CellFactoryManager<FxUnitMV>()
                .addCheckColumn(colActive)
                .provide();
        new ValueFactoryManager<FxUnitMV>()
                .addLong(colID, FxUnitMV::idProperty)
                .add(colActive, FxUnitMV::activeProperty)
                .add(colSymbol, FxUnitMV::symbolProperty)
                .add(colTag, FxUnitMV::tagProperty)
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
    void onRefreshDBAction() {
        FxListAllFlow.unit()
                .withBefore(data::clear)
                .withForEach(data::add)
                .withAfter(this::sortTable)
                .go();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onAddRowAction() {
        FxInsertFlow.unit()
                .withOnSuccess(data::add)
                .insert(top);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onEditRowAction() {
        Optional.ofNullable(tblData.getSelectionModel().getSelectedItem())
                .ifPresent(FxEditFlow.unit()
                        .withParent(top)
                        .withAfterSuccess(p -> EasyAlert
                                .editCompleted("Se completó la edición de la unidad " + p.getTag())
                                .run()));
    }

    /**
     * Resets the table sorting stuff.
     */
    private void sortTable() {
        colActive.setSortType(TableColumn.SortType.DESCENDING);
        colTag.setSortType(TableColumn.SortType.ASCENDING);
        colID.setSortType(TableColumn.SortType.ASCENDING);
        tblData.getSortOrder().setAll(Arrays.asList(colActive, colTag, colID));
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
    private class UserFiltering implements Callable<Predicate<FxUnitMV>>,
            Predicate<FxUnitMV> {
        /**
         * Holds the last compiled pattern when filter changed.
         */
        private Pattern ptn;

        @Override
        public Predicate<FxUnitMV> call() {
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
        public boolean test(@NotNull FxUnitMV x) {
            return ptn.matcher(x.getTag()).matches();
        }
    }
}
