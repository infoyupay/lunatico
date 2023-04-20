package com.yupay.lunatico.fxmview;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

/**
 * Snapshot of an {@link FxItem}
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxItemSnapshot {
    /**
     * The snapshot ID (one to one with {@link FxMovementLine}
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * The item id.
     */
    private final StringProperty itemId =
            new SimpleStringProperty(this, "itemId");
    /**
     * The item description/name.
     */
    private final StringProperty itemName =
            new SimpleStringProperty(this, "itemName");
    /**
     * The item measurement unit symbol.
     */
    private final StringProperty itemUnit =
            new SimpleStringProperty(this, "itemUnit");

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
     * @return value of {@link #itemId}.get();
     */
    public final String getItemId() {
        return itemId.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param itemId value to assign into {@link #itemId}.
     */
    public final void setItemId(String itemId) {
        this.itemId.set(itemId);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #itemId}.
     */
    public final StringProperty itemIdProperty() {
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
     * @return value of {@link #itemUnit}.get();
     */
    public final String getItemUnit() {
        return itemUnit.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param itemUnit value to assign into {@link #itemUnit}.
     */
    public final void setItemUnit(String itemUnit) {
        this.itemUnit.set(itemUnit);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #itemUnit}.
     */
    public final StringProperty itemUnitProperty() {
        return itemUnit;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxItemSnapshot that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
