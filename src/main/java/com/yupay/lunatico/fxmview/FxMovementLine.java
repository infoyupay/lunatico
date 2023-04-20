package com.yupay.lunatico.fxmview;

import javafx.beans.property.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * A line of detail in a movement record.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxMovementLine {
    /**
     * Global ID.
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * Line number (order) in the document.
     */
    private final IntegerProperty line =
            new SimpleIntegerProperty(this, "line");
    /**
     * Item snapshot holding item id, name and measurement unit symbol.
     */
    private final ObjectProperty<FxItemSnapshot> item =
            new SimpleObjectProperty<>(this, "item");
    /**
     * Moved quantity.
     */
    private final ObjectProperty<BigDecimal> quantity =
            new SimpleObjectProperty<>(this, "quantity", new BigDecimal("0.000"));
    /**
     * Remarks of the line.
     */
    private final StringProperty remark =
            new SimpleStringProperty(this, "remark");

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
     * @return value of {@link #line}.get();
     */
    public final int getLine() {
        return line.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param line value to assign into {@link #line}.
     */
    public final void setLine(int line) {
        this.line.set(line);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #line}.
     */
    public final IntegerProperty lineProperty() {
        return line;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #item}.get();
     */
    public final FxItemSnapshot getItem() {
        return item.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param item value to assign into {@link #item}.
     */
    public final void setItem(FxItemSnapshot item) {
        this.item.set(item);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #item}.
     */
    public final ObjectProperty<FxItemSnapshot> itemProperty() {
        return item;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #quantity}.get();
     */
    public final BigDecimal getQuantity() {
        return quantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param quantity value to assign into {@link #quantity}.
     */
    public final void setQuantity(BigDecimal quantity) {
        this.quantity.set(quantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #quantity}.
     */
    public final ObjectProperty<BigDecimal> quantityProperty() {
        return quantity;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #remark}.get();
     */
    public final String getRemark() {
        return remark.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param remark value to assign into {@link #remark}.
     */
    public final void setRemark(String remark) {
        this.remark.set(remark);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #remark}.
     */
    public final StringProperty remarkProperty() {
        return remark;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxMovementLine that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
