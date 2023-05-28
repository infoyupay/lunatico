package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxEditFlow;
import com.yupay.lunatico.fxflows.FxInsertFlow;
import com.yupay.lunatico.fxflows.FxListAllFlow;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxUnitMV;
import com.yupay.lunatico.fxtools.CellFactoryManager;
import com.yupay.lunatico.fxtools.Patterns;
import com.yupay.lunatico.fxtools.TextFilter;
import com.yupay.lunatico.fxtools.ValueFactoryManager;
import com.yupay.lunatico.model.ItemType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Master view for warehouse Items.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxItemView extends MasterView {
    /**
     * Underlying data to show in the table view.
     */
    private final ObservableList<FxItemMV> data
            = FXCollections.observableArrayList();


    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private Stage top;

    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private TableColumn<FxItemMV, Boolean> colActive;

    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private TableColumn<FxItemMV, Long> colID;

    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private TableColumn<FxItemMV, String> colName;

    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private TableColumn<FxItemMV, ItemType> colType;

    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private TableColumn<FxItemMV, FxUnitMV> colUnit;

    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private TableView<FxItemMV> tblData;

    /**
     * FXML control injected from item-view.fxml
     */
    @FXML
    private TextField txtFilter;

    /**
     * Fxml Initializer.
     */
    @FXML
    void initialize() {
        new CellFactoryManager<FxItemMV>()
                .addCheckColumn(colActive)
                .provide();

        new ValueFactoryManager<FxItemMV>()
                .addLong(colID, FxItemMV::idProperty)
                .add(colActive, FxItemMV::activeProperty)
                .add(colName, FxItemMV::nameProperty)
                .add(colType, FxItemMV::typeProperty)
                .add(colUnit, FxItemMV::unitProperty)
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
    void onAddRowAction() {
        FxInsertFlow.item()
                .withOnSuccess(data::add)
                .insert(top);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onEditRowAction() {
        Optional.ofNullable(tblData.getSelectionModel().getSelectedItem())
                .ifPresent(FxEditFlow.item()
                        .withParent(top)
                        .withAfterSuccess(p -> EasyAlert
                                .editCompleted(
                                        "Se completó la edición del artículo "
                                                + p.getId() + ": " + p.getName())
                                .run()));
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onRefreshDBAction() {
        FxListAllFlow.item()
                .withBefore(data::clear)
                .withForEach(data::add)
                .withAfter(this::resetSort)
                .go();
    }

    /**
     * Convenient method to reset the table sort order to a default.
     */
    private void resetSort() {
        colActive.setSortType(TableColumn.SortType.DESCENDING);
        colName.setSortType(TableColumn.SortType.ASCENDING);
        colType.setSortType(TableColumn.SortType.ASCENDING);
        colID.setSortType(TableColumn.SortType.ASCENDING);
        tblData.getSortOrder().setAll(Arrays.asList(colActive, colName, colType, colID));
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
    private class UserFilter implements Callable<Predicate<FxItemMV>>,
            Predicate<FxItemMV> {
        Pattern ptn;

        @Override
        public Predicate<FxItemMV> call() {
            var str = txtFilter.getText();
            if (str == null || str.isBlank()) {
                ptn = null;
                return x -> true;
            } else {
                ptn = Patterns.containsCI(str.strip());
                return this;
            }
        }

        @Override
        public boolean test(FxItemMV x) {
            return x != null
                    && ptn.matcher(x.getName()).matches();
        }
    }
}
