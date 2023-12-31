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

import com.yupay.lunatico.fxflows.FxListActiveFlow;
import com.yupay.lunatico.fxflows.FxListAllFlow;
import com.yupay.lunatico.fxflows.FxLoginFlow;
import com.yupay.lunatico.fxflows.FxMoveFlow;
import com.yupay.lunatico.fxmview.FxOvBalanceMV;
import com.yupay.lunatico.fxmview.FxUserMV;
import com.yupay.lunatico.fxmview.FxWarehouseMV;
import com.yupay.lunatico.fxtools.ValueFactoryManager;
import com.yupay.lunatico.model.ItemType;
import com.yupay.lunatico.toolbox.LocalFiles;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * The main scene controller with UI defined in lunatico.fxml.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class LunaticoScene implements EventHandler<WindowEvent> {
    /**
     * List to keep track of open children.
     */
    private final List<Stage> childrenWindow = new ArrayList<>();
    /**
     * Property to hold the currently logged-in user.
     */
    private final ObjectProperty<FxUserMV> loggedUser =
            new SimpleObjectProperty<>(this, "loggedUser");
    /**
     * Holds true if no user has been logged in.
     */
    private final ReadOnlyBooleanWrapper locked =
            new ReadOnlyBooleanWrapper(this, "locked");
    /**
     * Holds true if any user has been logged in.
     */
    private final ReadOnlyBooleanWrapper unlocked =
            new ReadOnlyBooleanWrapper(this, "unlocked");
    /**
     * Holds true if the logged user may assume the viewer role.
     */
    private final ReadOnlyBooleanWrapper roleViewer =
            new ReadOnlyBooleanWrapper(this, "roleViewer");
    /**
     * Holds true if the logged user may assume the business admin role.
     */
    private final ReadOnlyBooleanWrapper roleAdmin =
            new ReadOnlyBooleanWrapper(this, "roleAdmin");
    /**
     * Holds true if the logged user is a superuser.
     */
    private final ReadOnlyBooleanWrapper roleSudoer =
            new ReadOnlyBooleanWrapper(this, "roleSudoer");
    /**
     * Holds true if the logged user may assume the reporter role.
     */
    private final ReadOnlyBooleanWrapper roleReport =
            new ReadOnlyBooleanWrapper(this, "roleReport");
    /**
     * Holds true if it's unlocked AND the logged user is a sudoer.
     */
    private final ReadOnlyBooleanWrapper unlockedAndSudoer =
            new ReadOnlyBooleanWrapper(this, "unlockedAndSudoer", false);
    /**
     * The data to show in table view.
     */
    private final ObservableList<FxOvBalanceMV> balanceData =
            observableArrayList();
    /**
     * The primary stage window.
     */
    private Stage primaryStage;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxOvBalanceMV, String> colDescription;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxOvBalanceMV, ItemType> colType;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxOvBalanceMV, Long> colID;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxOvBalanceMV, BigDecimal> colQuantity;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxOvBalanceMV, String> colUnit;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxOvBalanceMV, BigDecimal> colUnitPrice;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxOvBalanceMV, BigDecimal> colCost;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableView<FxOvBalanceMV> tblData;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private Scene top;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private ComboBox<FxWarehouseMV> cboStore;

    @Override
    public void handle(@NotNull WindowEvent event) {
        if (event.getEventType() == WindowEvent.WINDOW_HIDDEN
                && event.getSource() instanceof Stage child) {
            childrenWindow.remove(child);
        } else if (event.getEventType() == WindowEvent.WINDOW_SHOWN
                && event.getSource() instanceof Stage child) {
            childrenWindow.add(child);
        }
    }

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        locked.bind(loggedUserProperty().isNull());
        unlocked.bind(loggedUserProperty().isNotNull());
        unlockedAndSudoer.bind(unlocked.and(roleSudoer));

        new ValueFactoryManager<FxOvBalanceMV>()
                .addLong(colID, FxOvBalanceMV::itemIdProperty)
                .add(colCost, FxOvBalanceMV::balanceCostProperty)
                .add(colDescription, FxOvBalanceMV::nameProperty)
                .add(colQuantity, FxOvBalanceMV::balanceUnitsProperty)
                .add(colUnit, FxOvBalanceMV::symbolProperty)
                .add(colUnitPrice, FxOvBalanceMV::balanceUnitCostProperty)
                .add(colType, FxOvBalanceMV::typeProperty)
                .provide();

        loggedUserProperty().addListener(new UserChanged());
        tblData.setItems(balanceData);
    }

    /**
     * Shows this scene in a primary stage window.
     *
     * @param primaryStage the primary stage window.
     */
    public void show(@NotNull Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lunático v1.0.0");
        this.primaryStage.setScene(top);
        this.primaryStage.setMaximized(true);
        this.primaryStage.show();
        try {
            var cnx = LocalFiles.getCnx();
            for (var i = 0; Files.notExists(cnx); i++) {
                onLocalSetupAction();
                if (i > 2) {
                    Platform.exit();
                    return;
                }
            }
        } catch (IOException e) {
            new ErrorHandler()
                    .withBriefing("No se pudo leer el archivo de configuración de conexión.")
                    .accept(e);
        }
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onLockAction() {
        setLoggedUser(null);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onUnlockAction() {
        new FxLoginFlow().login(this::setLoggedUser);
    }

    /**
     * FXML event handler.
     *
     * @param evt event object.
     */
    @FXML
    void onSysUserAction(@NotNull ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem mni)
            FxForms.userView().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     *
     * @param evt event object.
     */
    @FXML
    void onMgmWarehouse(@NotNull ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem mni)
            FxForms.warehouseView().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     *
     * @param evt event object.
     */
    @FXML
    void onSnapshotAction(@NotNull ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem mni)
            FxForms.snapshotView().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     *
     * @param evt event object.
     */
    @FXML
    void onMgmItems(@NotNull ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem mni)
            FxForms.itemView().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     *
     * @param evt event object.
     */
    @FXML
    void onMgmUnits(@NotNull ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem mni)
            FxForms.unitView().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     *
     * @param evt event object.
     */
    @FXML
    void onMgmFolioType(@NotNull ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem mni)
            FxForms.folioTypeView().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     *
     * @param evt event object.
     */
    @FXML
    void onMgmPerson(@NotNull ActionEvent evt) {
        if (evt.getSource() instanceof MenuItem mni)
            FxForms.personView().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onCreateMovAction() {
        new FxMoveFlow().withOwner(primaryStage).showCreator();
    }

    /**
     * FXML event handler.
     *
     * @param event the event object.
     */
    @FXML
    void onTableClicked(@NotNull MouseEvent event) {
        if (event.isConsumed() || event.getClickCount() < 2) return;
        event.consume();
        var sel = tblData.getSelectionModel().getSelectedItem();
        if (sel != null) {
            var form = FxForms.kardexView();
            form.fetchAndSet(sel.getItemId());
            form.setWarehouse(cboStore.getValue());
            form.show(primaryStage);
        }
    }

    /**
     * FXML event handler.
     *
     * @param event the event object.
     */
    @FXML
    void onItemTrendAction(@NotNull ActionEvent event) {
        if (event.getSource() instanceof MenuItem mni)
            FxForms.itemTrend().showAndWait(mni.disableProperty());
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onExitAction() {
        EasyAlert.warning()
                .withTitle("Confirmar Cierre")
                .withHeaderText("¿Estás seguro que quieres salir?")
                .withContentText("Salir de la aplicación hará que los datos " +
                        "que no hayas enviado a la base de datos se pierdan. " +
                        "Si alguna operación está en curso nunca sabremos el resultado.")
                .buttonYesNoCancel()
                .showAndExpect(ButtonType.YES, Platform::exit);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onSyncAction() {
        var ware = cboStore.getSelectionModel().getSelectedItem();
        if (ware == null) {
            EasyAlert.error()
                    .withTitle("Error")
                    .withHeaderText("Selecciona un almacén primero.")
                    .withContentText("Para poder mostrar el estado actual, debes elegir el almacén.")
                    .buttonOkOnly()
                    .showAndWait();
        } else {
            FxListAllFlow.balanceOverview(ware)
                    .withBefore(balanceData::clear)
                    .withForEach(balanceData::add)
                    .withAfter(() -> tblData.getSortOrder().setAll(List.of(colID)))
                    .go();
        }
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onLocalSetupAction() {
        //Create GUI component.
        var ui = new FileChooser();
        ui.setTitle("Importar conexión");
        var filter = new ExtensionFilter(
                "Archivo de Configuración (*.properties)",
                "*.properties");
        ui.getExtensionFilters().setAll(
                filter,
                new ExtensionFilter("Todos los archivos (*.*)", "*.*"));
        ui.setSelectedExtensionFilter(filter);
        //Show and get selected file.
        var file = ui.showOpenDialog(primaryStage);
        if (file != null) {
            var in = file.toPath();
            try {
                var out = LocalFiles.getCnx();
                Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            } catch (IOException e) {
                new ErrorHandler()
                        .withBriefing("Ocurrió un error al instalar el archivo de configuración.")
                        .accept(e);
            }
        }
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #loggedUser}.get();
     */
    public final FxUserMV getLoggedUser() {
        return loggedUser.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param loggedUser value to assign into {@link #loggedUser}.
     */
    public final void setLoggedUser(FxUserMV loggedUser) {
        this.loggedUser.set(loggedUser);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #loggedUser}.
     */
    public final ObjectProperty<FxUserMV> loggedUserProperty() {
        return loggedUser;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #locked}.get();
     */
    public final boolean isLocked() {
        return locked.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #locked}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty lockedProperty() {
        return locked.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #unlocked}.get();
     */
    public final boolean isUnlocked() {
        return unlocked.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #unlocked}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty unlockedProperty() {
        return unlocked.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #roleViewer}.get();
     */
    public final boolean isRoleViewer() {
        return roleViewer.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleViewer}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty roleViewerProperty() {
        return roleViewer.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #roleAdmin}.get();
     */
    public final boolean isRoleAdmin() {
        return roleAdmin.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleAdmin}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty roleAdminProperty() {
        return roleAdmin.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #roleSudoer}.get();
     */
    public final boolean isRoleSudoer() {
        return roleSudoer.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleSudoer}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty roleSudoerProperty() {
        return roleSudoer.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #roleReport}.get();
     */
    public final boolean isRoleReport() {
        return roleReport.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleReport}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty roleReportProperty() {
        return roleReport.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #unlockedAndSudoer}.get();
     */
    public final boolean isUnlockedAndSudoer() {
        return unlockedAndSudoer.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #unlockedAndSudoer}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty unlockedAndSudoerProperty() {
        return unlockedAndSudoer.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #balanceData}.get();
     */
    public final ObservableList<FxOvBalanceMV> getBalanceData() {
        return balanceData;
    }


    /**
     * Synchronizes roles when user changes.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class UserChanged implements ChangeListener<FxUserMV> {
        @Override
        public void changed(ObservableValue<? extends FxUserMV> observable,
                            FxUserMV oldValue,
                            FxUserMV n) {
            //Clear roles always.
            Stream.of(
                            roleAdmin,
                            roleReport,
                            roleSudoer,
                            roleViewer)
                    .forEach(w -> w.setValue(false));
            //If new value is null or not active, no role should be granted
            // and a lockdown should be performed.
            if (n == null || !n.isActive()) {
                /*#**********************************************************************
                 * First extract to an array, so concurrent modification is avoided.    *
                 *                                                                      *
                 * DO NOT MODIFY IF YOU DON'T UNDERSTAND WHY IT DOES WORK. THOROUGHLY   *
                 * READ THE REMARKS BEFORE TRYING TO TOUCH THIS.                        *
                 ************************************************************************/
                for (var wdw : childrenWindow.toArray(Stage[]::new)) {
                    /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*
                    ! This would cause a concurrent modification exception                 !
                    ! if invoked directly as childrenWindow.forEach(Stage::close),         !
                    ! this is because each added children is deleted from childrenWindow   !
                    ! when stage fires the hidden event.                                   !
                    *!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
                    wdw.close();
                }
                //Clean data
                balanceData.clear();
                cboStore.setValue(null);
                cboStore.setItems(observableArrayList());
                return;
            }
            //The sudoer role, only by the sudoer.
            roleSudoer.set(n.isRoleAdminSys());
            //The sudoer and business admin are business admins.
            roleAdmin.set(n.isRoleAdmin()
                    || n.isRoleAdminSys());
            //The sudoer, business admin and reporter are reporters.
            roleReport.set(n.isRoleReporter()
                    || n.isRoleAdmin()
                    || n.isRoleAdminSys());
            //The viewer is the lowest role of all.
            roleViewer.set(n.isRoleViewer()
                    || n.isRoleReporter()
                    || n.isRoleAdmin()
                    || n.isRoleAdminSys());
            //If is at least viewer, will load combo box.
            if (isRoleViewer()) {
                FxListActiveFlow.warehouse()
                        .withForEach(cboStore.getItems()::add)
                        .go();
            }
        }
    }
}

