package com.yupay.lunatico.fxmview;

import com.yupay.lunatico.model.ItemType;
import com.yupay.lunatico.model.ModelView;
import com.yupay.lunatico.model.OvBalance;
import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * The balance overview model-view entity for JavaFX.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxOvBalanceMV extends ModelView<OvBalance, FxOvBalanceMV> {
    /**
     * The primary key (balance's id).
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * Item's name.
     */
    private final StringProperty name =
            new SimpleStringProperty(this, "name");
    /**
     * Type of item.
     */
    private final ObjectProperty<ItemType> type =
            new SimpleObjectProperty<>(this, "type");
    /**
     * Symbol for measurement unit.
     */
    private final StringProperty symbol =
            new SimpleStringProperty(this, "symbol");
    /**
     * Warehouse id.
     */
    private final LongProperty warehouse =
            new SimpleLongProperty(this, "warehouse");
    /**
     * Balance units quantity.
     */
    private final ObjectProperty<BigDecimal> balanceUnits =
            new SimpleObjectProperty<>(this, "balanceUnits", new BigDecimal("0.00000000"));
    /**
     * Balance cost quantity.
     */
    private final ObjectProperty<BigDecimal> balanceCost =
            new SimpleObjectProperty<>(this, "balanceCost", new BigDecimal("0.00000000"));
    /**
     * Balance unitary cost/price.
     */
    private final ObjectProperty<BigDecimal> balanceUnitCost =
            new SimpleObjectProperty<>(this, "balanceUnitCost", new BigDecimal("0.00000000"));
    /**
     * The warehouse item's id.
     */
    private final LongProperty itemId =
            new SimpleLongProperty(this, "itemId");
    /**
     * The warehouse's name.
     */
    private final StringProperty warehouseName =
            new SimpleStringProperty(this, "warehouseName");

    /**
     * Constructor to copy information from a model entity.
     *
     * @param model the model entity.
     */
    public FxOvBalanceMV(@NotNull OvBalance model) {
        fromModel(model);
    }

    /**
     * Default no-op constructor.
     */
    public FxOvBalanceMV() {
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #id}.get();
     */
    public final long getId() {
        return id.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param id value to assign into {@link #id}.
     */
    public final void setId(long id) {
        this.id.set(id);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #id}.
     */
    public final LongProperty idProperty() {
        return id;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #name}.get();
     */
    public final String getName() {
        return name.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param name value to assign into {@link #name}.
     */
    public final void setName(String name) {
        this.name.set(name);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #name}.
     */
    public final StringProperty nameProperty() {
        return name;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #type}.get();
     */
    public final ItemType getType() {
        return type.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param type value to assign into {@link #type}.
     */
    public final void setType(ItemType type) {
        this.type.set(type);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #type}.
     */
    public final ObjectProperty<ItemType> typeProperty() {
        return type;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #symbol}.get();
     */
    public final String getSymbol() {
        return symbol.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param symbol value to assign into {@link #symbol}.
     */
    public final void setSymbol(String symbol) {
        this.symbol.set(symbol);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #symbol}.
     */
    public final StringProperty symbolProperty() {
        return symbol;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #warehouse}.get();
     */
    public final long getWarehouse() {
        return warehouse.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param warehouse value to assign into {@link #warehouse}.
     */
    public final void setWarehouse(long warehouse) {
        this.warehouse.set(warehouse);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #warehouse}.
     */
    @SuppressWarnings("unused")
    public final LongProperty warehouseProperty() {
        return warehouse;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #balanceUnits}.get();
     */
    public final BigDecimal getBalanceUnits() {
        return balanceUnits.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param balanceUnits value to assign into {@link #balanceUnits}.
     */
    public final void setBalanceUnits(BigDecimal balanceUnits) {
        this.balanceUnits.set(balanceUnits);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #balanceUnits}.
     */
    public final ObjectProperty<BigDecimal> balanceUnitsProperty() {
        return balanceUnits;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #balanceCost}.get();
     */
    public final BigDecimal getBalanceCost() {
        return balanceCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param balanceCost value to assign into {@link #balanceCost}.
     */
    public final void setBalanceCost(BigDecimal balanceCost) {
        this.balanceCost.set(balanceCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #balanceCost}.
     */
    public final ObjectProperty<BigDecimal> balanceCostProperty() {
        return balanceCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #balanceUnitCost}.get();
     */
    public final BigDecimal getBalanceUnitCost() {
        return balanceUnitCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param balanceUnitCost value to assign into {@link #balanceUnitCost}.
     */
    public final void setBalanceUnitCost(BigDecimal balanceUnitCost) {
        this.balanceUnitCost.set(balanceUnitCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #balanceUnitCost}.
     */
    public final ObjectProperty<BigDecimal> balanceUnitCostProperty() {
        return balanceUnitCost;
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
    @SuppressWarnings("unused")
    public final StringProperty warehouseNameProperty() {
        return warehouseName;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxOvBalanceMV that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull FxOvBalanceMV deepCopy() {
        var r = new FxOvBalanceMV();
        r.setBalanceCost(getBalanceCost());
        r.setBalanceCost(getBalanceCost());
        r.setBalanceUnitCost(getBalanceUnitCost());
        r.setBalanceUnits(getBalanceUnits());
        r.setId(getId());
        r.setName(getName());
        r.setSymbol(getSymbol());
        r.setType(getType());
        r.setWarehouse(getWarehouse());
        r.setWarehouseName(getWarehouseName());
        r.setItemId(getItemId());
        return r;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull OvBalance toModel() {
        var r = new OvBalance();
        r.setBalanceCost(getBalanceCost());
        r.setBalanceUnitCost(getBalanceUnitCost());
        r.setBalanceUnits(getBalanceUnits());
        r.setId(getId());
        r.setName(getName());
        r.setSymbol(getSymbol());
        r.setType(getType());
        r.setWarehouse(getWarehouse());
        r.setWarehouseName(getWarehouseName());
        r.setItemId(getItemId());
        return r;
    }

    @Override
    public void fromModel(@NotNull OvBalance m) {
        setBalanceCost(m.getBalanceCost());
        setBalanceUnitCost(m.getBalanceUnitCost());
        setBalanceUnits(m.getBalanceUnits());
        setId(m.getId());
        setName(m.getName());
        setSymbol(m.getSymbol());
        setType(m.getType());
        setWarehouse(m.getWarehouse());
        setWarehouseName(m.getWarehouseName());
        setItemId(m.getItemId());
    }
}
