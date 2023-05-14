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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
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
 * Master view for Fx User management.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUserView {
    /**
     * The data to show in the table view after filtering and sorting.
     */
    private final ObservableList<FxUserMV> data
            = FXCollections.observableArrayList();
    /**
     * Property to show if the table selection is empty.
     * Visible for FXML purposes.
     */
    private final ReadOnlyBooleanWrapper emptySelection =
            new ReadOnlyBooleanWrapper(this, "emptySelection");
    /**
     * External disabled flags to change when closing this stage.
     */
    private List<BooleanProperty> disabled;
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

        emptySelection
                .bind(tblData.getSelectionModel().selectedItemProperty().isNull());
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
    void onStageClosed() {
        if (disabled != null) disabled.forEach(b -> b.set(false));
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onStageShown() {
        if (disabled != null) disabled.forEach(b -> b.set(true));
        onRefreshDBAction();
    }

    /**
     * FXML event handler.
     *
     * @param event the event object.
     */
    @FXML
    void onTableDataClicked(@NotNull MouseEvent event) {
        if (event.isConsumed()) return;
        if (event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() > 1) onEditAction();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onEditAction() {
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
     * Initializes the window owner, and then
     * window moadlity to APPLICATION_MODAL,
     * and then shows and wait.
     *
     * @param owner    the parent window.
     * @param master   an external event handler that keeps
     *                 track of opened and closed children windows
     *                 so they can be closed with the lock option.
     * @param disabled external boolean properties to disable controls
     *                 while this windows is kept open.
     * @see Stage#initOwner(Window)
     * @see Stage#showAndWait()
     */
    public void showAndWait(@NotNull Stage owner,
                            @NotNull EventHandler<WindowEvent> master,
                            @Nullable BooleanProperty... disabled) {
        if (disabled != null) this.disabled = Arrays.asList(disabled);
        top.addEventHandler(WindowEvent.ANY, master);
        top.initOwner(owner);
        top.initModality(Modality.NONE);
        top.showAndWait();
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

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #emptySelection}.get();
     */
    public final boolean isEmptySelection() {
        return emptySelection.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #emptySelection}.
     */
    public final ReadOnlyBooleanProperty emptySelectionProperty() {
        return emptySelection.getReadOnlyProperty();
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
