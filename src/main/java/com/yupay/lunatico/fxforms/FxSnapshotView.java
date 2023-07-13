package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxCreateBalancesFlow;
import com.yupay.lunatico.fxflows.FxListLiveSnapshotFlow;
import com.yupay.lunatico.fxtools.FxSnapshotTreeItem;
import com.yupay.lunatico.fxtools.TreeValueFactoryManager;
import com.yupay.lunatico.model.ItemType;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * Snapshot preview window.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxSnapshotView extends MasterView {

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, BigDecimal> colGlobalCost;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, BigDecimal> colGlobalQuantity;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, BigDecimal> colGlobalUnitCost;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, Long> colItemID;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, String> colItemName;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, ItemType> colItemType;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, BigDecimal> colLocalCost;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, BigDecimal> colLocalQuantity;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, BigDecimal> colLocalUnitCost;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, String> colUnit;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, Long> colWarehouseID;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableColumn<FxSnapshotTreeItem, String> colWarehouseName;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private TreeTableView<FxSnapshotTreeItem> tblData;

    /**
     * FXML control injected from snapshot-view.fxml
     */
    @FXML
    private Stage top;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        new TreeValueFactoryManager<FxSnapshotTreeItem>()
                .addLong(colItemID, FxSnapshotTreeItem::itemIdProperty)
                .addLong(colWarehouseID, FxSnapshotTreeItem::warehouseIdProperty)
                .add(colGlobalCost, FxSnapshotTreeItem::globalCostProperty)
                .add(colGlobalQuantity, FxSnapshotTreeItem::globalQuantityProperty)
                .add(colGlobalUnitCost, FxSnapshotTreeItem::globalUnitCostProperty)
                .add(colItemName, FxSnapshotTreeItem::itemNameProperty)
                .add(colItemType, FxSnapshotTreeItem::itemTypeProperty)
                .add(colLocalCost, FxSnapshotTreeItem::localCostProperty)
                .add(colLocalQuantity, FxSnapshotTreeItem::localQuantityProperty)
                .add(colLocalUnitCost, FxSnapshotTreeItem::localUnitCostProperty)
                .add(colUnit, FxSnapshotTreeItem::unitProperty)
                .add(colWarehouseName, FxSnapshotTreeItem::warehouseNameProperty)
                .provide();

    }

    /**
     * FXML event handler.
     */
    @FXML
    void onRefreshAction() {
        onRefreshDBAction();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onSnapshotAction() {
        new FxCreateBalancesFlow().go();
    }

    @Override
    void onRefreshDBAction() {
        new FxListLiveSnapshotFlow().go(tblData::setRoot);
    }

    @Override
    protected @NotNull Stage getTop() {
        return top;
    }

    @Override
    void onEditRowAction() {
        //DO NOTHING!
    }
}
