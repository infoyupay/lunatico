package com.yupay.lunatico.fxmview;

import javafx.beans.property.*;

/**
 * The types of folio.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxFolioType {
    /**
     * The ID of the folio type.
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * Name of type.
     */
    private final StringProperty name =
            new SimpleStringProperty(this, "name");
    /**
     * Active flag.
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

}
