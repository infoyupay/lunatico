package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxEditFlow;
import com.yupay.lunatico.fxflows.FxInsertFlow;
import com.yupay.lunatico.fxflows.FxListAllFlow;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxUnitMV;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.ItemType;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
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
public class FxItemView {
    /**
     * Underlying data to show in the table view.
     */
    private final ObservableList<FxItemMV> data
            = FXCollections.observableArrayList();

    /**
     * Collection of external disabled properties to update when leaving this view.
     */
    private List<BooleanProperty> externalDisabled;

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
     * FXML event handler.
     *
     * @param event the event object.
     */
    @FXML
    void onTableDataClicked(@NotNull MouseEvent event) {
        if (!event.isConsumed()
                && event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() > 1) {
            event.consume();
            onEditRowAction();
        }
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onStageShown() {
        if (externalDisabled != null) externalDisabled.forEach(Functionals.setValue(true));
        onRefreshDBAction();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onStageClosed() {
        if (externalDisabled != null) externalDisabled.forEach(Functionals.setValue(false));
    }

    /**
     * Initializes the window owner, and then
     * window moadlity to APPLICATION_MODAL,
     * and then shows and wait.
     *
     * @param externalDisabled external boolean properties to disable controls
     *                         while this windows is kept open.
     * @see Stage#showAndWait()
     */
    public void showAndWait(@Nullable BooleanProperty... externalDisabled) {
        if (externalDisabled != null) this.externalDisabled = Arrays.asList(externalDisabled);
        top.addEventHandler(WindowEvent.ANY, FxLunatico.APP_CONTROLLER);
        top.initOwner(FxLunatico.PRIMARY);
        top.initModality(Modality.NONE);
        top.showAndWait();
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
