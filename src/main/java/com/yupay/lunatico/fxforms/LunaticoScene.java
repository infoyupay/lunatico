package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.Prototypes;
import com.yupay.lunatico.fxflows.FxLoginFlow;
import com.yupay.lunatico.fxmview.FxItem;
import com.yupay.lunatico.fxmview.FxStore;
import com.yupay.lunatico.fxmview.FxUnit;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private ComboBox<FxStore> cboStore;
    /**
     * FXML control injected from lunatico.fxml
     */
    @FXML
    private MenuItem mniSysUser;

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
        cboStore.setItems(Prototypes.STORES);
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
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onUnlockAction() {
        new FxLoginFlow().login(p -> System.out.println(p.getId()));
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onSysUserAction() {
        FxForms.userView().showAndWait(primaryStage, this,
                mniSysUser.disableProperty());
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
}
