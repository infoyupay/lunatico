package com.yupay.lunatico.fxmview;

import javafx.beans.property.*;

import java.util.Objects;

/**
 * Third parties in the movements.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxParty {
    /**
     * Autoincremental ID.
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * Identification card number.
     */
    private final StringProperty doiNum =
            new SimpleStringProperty(this, "doiNum");
    /**
     * Name of party.
     */
    private final StringProperty name =
            new SimpleStringProperty(this, "name");
    /**
     * Tag of the person.
     */
    private final StringProperty tag =
            new SimpleStringProperty(this, "tag");
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
     * @return value of {@link #doiNum}.get();
     */
    public final String getDoiNum() {
        return doiNum.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param doiNum value to assign into {@link #doiNum}.
     */
    public final void setDoiNum(String doiNum) {
        this.doiNum.set(doiNum);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #doiNum}.
     */
    public final StringProperty doiNumProperty() {
        return doiNum;
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
     * @return value of {@link #tag}.get();
     */
    public final String getTag() {
        return tag.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param tag value to assign into {@link #tag}.
     */
    public final void setTag(String tag) {
        this.tag.set(tag);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #tag}.
     */
    public final StringProperty tagProperty() {
        return tag;
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
        return this == o || o instanceof FxParty fxParty &&
                getId() == fxParty.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
