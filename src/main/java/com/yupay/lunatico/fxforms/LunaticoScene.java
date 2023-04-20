package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.Prototypes;
import com.yupay.lunatico.fxmview.FxItem;
import com.yupay.lunatico.fxmview.FxStore;
import com.yupay.lunatico.fxmview.FxUnit;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class LunaticoScene {

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
