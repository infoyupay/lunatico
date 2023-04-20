package com.yupay.lunatico.fxmview;

import javafx.beans.property.*;

import java.util.Objects;

/**
 * Measurement units.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUnit {
    /**
     * Measurement unit id (autoincremental).
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * Long name of the unit.
     */
    private final StringProperty name =
            new SimpleStringProperty(this, "name");
    /**
     * Symbol of the unit.
     */
    private final StringProperty symbol =
            new SimpleStringProperty(this, "symbol");
    /**
     * Active flag, if false should be treated as if deleted.
     */
    private final BooleanProperty active =
            new SimpleBooleanProperty(this, "active");

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
     * @return value of {@link #active}.get();
     */
    public final boolean isActive() {
        return active.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param active value to assign into {@link #active}.
     */
    public final void setActive(boolean active) {
        this.active.set(active);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #active}.
     */
    public final BooleanProperty activeProperty() {
        return active;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxUnit fxUnit &&
                getId() == fxUnit.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
