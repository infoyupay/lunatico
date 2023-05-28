package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.Prototypes;
import com.yupay.lunatico.fxflows.FxListActiveFlow;
import com.yupay.lunatico.fxflows.FxLoginFlow;
import com.yupay.lunatico.fxmview.FxItem;
import com.yupay.lunatico.fxmview.FxUnit;
import com.yupay.lunatico.fxmview.FxUserMV;
import com.yupay.lunatico.fxmview.FxWarehouseMV;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
     * The primary stage window.
     */
    private Stage primaryStage;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, String> colDescription;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, Long> colID;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colInAdj;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colInFreight;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colInProduction;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colInPurchase;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colInReturn;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colOutAdj;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colOutFreight;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colOutProduction;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colOutReturn;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colOutSale;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colOutWaste;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colStock;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, FxUnit> colUnit;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colSaved;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colTransit;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colStore;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colSale;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableColumn<FxItem, BigDecimal> colOutGift;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private TableView<FxItem> tblData;
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
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private CheckBox chkStore;

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
        //<editor-fold desc="Fool implementation to be removed.">
        //TODO: REMOVE THIS ENTIRE SECTION AFTER REAL IMPLEMENTATION!!
        tblData.setItems(FXCollections.observableList(Prototypes.items()));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colInAdj.setCellValueFactory(new PropertyValueFactory<>("inFix"));
        colInFreight.setCellValueFactory(new PropertyValueFactory<>("inTransfer"));
        colInProduction.setCellValueFactory(new PropertyValueFactory<>("inProduction"));
        colInPurchase.setCellValueFactory(new PropertyValueFactory<>("inPurchase"));
        colInReturn.setCellValueFactory(new PropertyValueFactory<>("inReturn"));
        colOutAdj.setCellValueFactory(new PropertyValueFactory<>("outFix"));
        colOutFreight.setCellValueFactory(new PropertyValueFactory<>("outTransfer"));
        colOutGift.setCellValueFactory(new PropertyValueFactory<>("outGift"));
        colOutProduction.setCellValueFactory(new PropertyValueFactory<>("outProduction"));
        colOutReturn.setCellValueFactory(new PropertyValueFactory<>("outReturn"));
        colOutSale.setCellValueFactory(new PropertyValueFactory<>("outSale"));
        colOutWaste.setCellValueFactory(new PropertyValueFactory<>("outWaste"));
        colSale.setCellValueFactory(new PropertyValueFactory<>("balanceSale"));
        colSaved.setCellValueFactory(new PropertyValueFactory<>("balanceSaved"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStore.setCellValueFactory(new PropertyValueFactory<>("balanceStore"));
        colTransit.setCellValueFactory(new PropertyValueFactory<>("balanceTransit"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        //</editor-fold>

        locked.bind(loggedUserProperty().isNull());
        unlocked.bind(loggedUserProperty().isNotNull());
        unlockedAndSudoer.bind(unlocked.and(roleSudoer));

        loggedUserProperty().addListener(new UserChanged());
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
     */
    @FXML
    void onCreateMovAction() {
        var crd = FxForms.movementCard();
        crd.initOwner(primaryStage);
        crd.initModality(Modality.APPLICATION_MODAL);
        crd.showAndWait();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onItemTrendAction() {
        FxForms.itemTrend().showAndWait();
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
                cboStore.setValue(null);
                cboStore.setItems(FXCollections.observableArrayList());
                chkStore.setSelected(false);
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

