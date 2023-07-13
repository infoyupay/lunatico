package com.yupay.lunatico.fxtools;

import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.ItemType;
import com.yupay.lunatico.model.OvBalance;
import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Tree view item for the balances Snapshot.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxSnapshotTreeItem {
    /**
     * Object ID.
     */
    private final UUID _oid = UUID.randomUUID();
    /**
     * The item ID.
     * All elements should contain this value.
     */
    private final LongProperty itemId =
            new SimpleLongProperty(this, "itemId");
    /**
     * The item name.
     * Elements at node level of item should contain this value.
     */
    private final StringProperty itemName =
            new SimpleStringProperty(this, "itemName");
    /**
     * The warehouse ID.
     * This value should be present only in local balance level.
     */
    private final LongProperty warehouseId =
            new SimpleLongProperty(this, "warehouseId");
    /**
     * The warehouse name.
     * This value should be present only in local balance level.
     */
    private final StringProperty warehouseName =
            new SimpleStringProperty(this, "warehouseName");
    /**
     * The type of warehouse item.
     * Elements at node level of item should contain this value.
     */
    private final ObjectProperty<ItemType> itemType =
            new SimpleObjectProperty<>(this, "itemType");
    /**
     * The measurement unit.
     * Elements at node level of item should contain this value.
     */
    private final StringProperty unit =
            new SimpleStringProperty(this, "unit");
    /**
     * The global balance quantity.
     * Elements at node level of item should contain this value.
     */
    private final ObjectProperty<BigDecimal> globalQuantity =
            new SimpleObjectProperty<>(this, "globalQuantity");
    /**
     * The global cost per unit.
     * Elements at node level of item should contain this value.
     */
    private final ObjectProperty<BigDecimal> globalUnitCost =
            new SimpleObjectProperty<>(this, "globalUnitCost");
    /**
     * The global total balance cost, this is:
     * {@link #globalQuantity} * {@link #globalUnitCost}.
     * Elements at node level of item should contain this value.
     */
    private final ObjectProperty<BigDecimal> globalCost =
            new SimpleObjectProperty<>(this, "globalCost");
    /**
     * The local balance (quantity).
     */
    private final ObjectProperty<BigDecimal> localQuantity =
            new SimpleObjectProperty<>(this, "localQuantity");
    /**
     * The local cost per unit.
     */
    private final ObjectProperty<BigDecimal> localUnitCost =
            new SimpleObjectProperty<>(this, "localUnitCost");
    /**
     * The local balance cost, this is:
     * {@link #localQuantity} * {@link #localUnitCost}
     */
    private final ObjectProperty<BigDecimal> localCost =
            new SimpleObjectProperty<>(this, "localCost");

    /**
     * Empty constructor, useful to create the root item.
     */
    public FxSnapshotTreeItem() {
        setItemName("Costo Global del Inventario");
        setGlobalCost(BigDecimal.ZERO);
    }

    /**
     * Constructor to build this instance from a warehouse item.
     *
     * @param item the warehouse item.
     */
    public FxSnapshotTreeItem(@NotNull Item item) {
        setItemId(item.getId());
        setItemName(item.getName());
        setItemType(item.getType());
        setUnit(item.getUnit().getSymbol());
        setGlobalCost(item.getBalanceCost());
        setGlobalQuantity(item.getBalanceUnits());
        setGlobalUnitCost(item.getBalanceUnitCost());
    }

    /**
     * Constructor to build this instance from a local balance for a given item.
     *
     * @param balance the balance entity.
     */
    public FxSnapshotTreeItem(@NotNull OvBalance balance) {
        setLocalCost(balance.getBalanceCost());
        setLocalQuantity(balance.getBalanceUnits());
        setLocalUnitCost(balance.getBalanceUnitCost());
        setWarehouseId(balance.getWarehouse());
        setWarehouseName(balance.getWarehouseName());
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #itemId}.get();
     */
    public final long getItemId() {
        return itemId.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param itemId value to assign into {@link #itemId}.
     */
    public final void setItemId(long itemId) {
        this.itemId.set(itemId);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #itemId}.
     */
    public final LongProperty itemIdProperty() {
        return itemId;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #itemName}.get();
     */
    public final String getItemName() {
        return itemName.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param itemName value to assign into {@link #itemName}.
     */
    public final void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #itemName}.
     */
    public final StringProperty itemNameProperty() {
        return itemName;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #warehouseId}.get();
     */
    public final long getWarehouseId() {
        return warehouseId.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param warehouseId value to assign into {@link #warehouseId}.
     */
    public final void setWarehouseId(long warehouseId) {
        this.warehouseId.set(warehouseId);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #warehouseId}.
     */
    public final LongProperty warehouseIdProperty() {
        return warehouseId;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #warehouseName}.get();
     */
    public final String getWarehouseName() {
        return warehouseName.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param warehouseName value to assign into {@link #warehouseName}.
     */
    public final void setWarehouseName(String warehouseName) {
        this.warehouseName.set(warehouseName);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #warehouseName}.
     */
    public final StringProperty warehouseNameProperty() {
        return warehouseName;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #itemType}.get();
     */
    public final ItemType getItemType() {
        return itemType.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param itemType value to assign into {@link #itemType}.
     */
    public final void setItemType(ItemType itemType) {
        this.itemType.set(itemType);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #itemType}.
     */
    public final ObjectProperty<ItemType> itemTypeProperty() {
        return itemType;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #unit}.get();
     */
    public final String getUnit() {
        return unit.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param unit value to assign into {@link #unit}.
     */
    public final void setUnit(String unit) {
        this.unit.set(unit);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #unit}.
     */
    public final StringProperty unitProperty() {
        return unit;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #globalQuantity}.get();
     */
    public final BigDecimal getGlobalQuantity() {
        return globalQuantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param globalQuantity value to assign into {@link #globalQuantity}.
     */
    public final void setGlobalQuantity(BigDecimal globalQuantity) {
        this.globalQuantity.set(globalQuantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #globalQuantity}.
     */
    public final ObjectProperty<BigDecimal> globalQuantityProperty() {
        return globalQuantity;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #globalUnitCost}.get();
     */
    public final BigDecimal getGlobalUnitCost() {
        return globalUnitCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param globalUnitCost value to assign into {@link #globalUnitCost}.
     */
    public final void setGlobalUnitCost(BigDecimal globalUnitCost) {
        this.globalUnitCost.set(globalUnitCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #globalUnitCost}.
     */
    public final ObjectProperty<BigDecimal> globalUnitCostProperty() {
        return globalUnitCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #globalCost}.get();
     */
    public final BigDecimal getGlobalCost() {
        return globalCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param globalCost value to assign into {@link #globalCost}.
     */
    public final void setGlobalCost(BigDecimal globalCost) {
        this.globalCost.set(globalCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #globalCost}.
     */
    public final ObjectProperty<BigDecimal> globalCostProperty() {
        return globalCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #localQuantity}.get();
     */
    public final BigDecimal getLocalQuantity() {
        return localQuantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param localQuantity value to assign into {@link #localQuantity}.
     */
    public final void setLocalQuantity(BigDecimal localQuantity) {
        this.localQuantity.set(localQuantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #localQuantity}.
     */
    public final ObjectProperty<BigDecimal> localQuantityProperty() {
        return localQuantity;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #localUnitCost}.get();
     */
    public final BigDecimal getLocalUnitCost() {
        return localUnitCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param localUnitCost value to assign into {@link #localUnitCost}.
     */
    public final void setLocalUnitCost(BigDecimal localUnitCost) {
        this.localUnitCost.set(localUnitCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #localUnitCost}.
     */
    public final ObjectProperty<BigDecimal> localUnitCostProperty() {
        return localUnitCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #localCost}.get();
     */
    public final BigDecimal getLocalCost() {
        return localCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param localCost value to assign into {@link #localCost}.
     */
    public final void setLocalCost(BigDecimal localCost) {
        this.localCost.set(localCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #localCost}.
     */
    public final ObjectProperty<BigDecimal> localCostProperty() {
        return localCost;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxSnapshotTreeItem that &&
                Objects.equals(_oid, that._oid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_oid);
    }

}
