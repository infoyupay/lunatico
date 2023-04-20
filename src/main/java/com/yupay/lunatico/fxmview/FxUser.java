package com.yupay.lunatico.fxmview;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

/**
 * The user object for model-view.
 * It shouldn't contain the password.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUser {
    /**
     * The user ID (login).
     */
    private final StringProperty id =
            new SimpleStringProperty(this, "id");
    /**
     * Active flag, if false should be treated as if deleted.
     */
    private final BooleanProperty active =
            new SimpleBooleanProperty(this, "active");
    /**
     * Full, real name.
     */
    private final StringProperty realName =
            new SimpleStringProperty(this, "realName");

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #id}.get();
     */
    public final String getId() {
        return id.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param id value to assign into {@link #id}.
     */
    public final void setId(String id) {
        this.id.set(id);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #id}.
     */
    public final StringProperty idProperty() {
        return id;
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

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #realName}.get();
     */
    public final String getRealName() {
        return realName.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param realName value to assign into {@link #realName}.
     */
    public final void setRealName(String realName) {
        this.realName.set(realName);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #realName}.
     */
    public final StringProperty realNameProperty() {
        return realName;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxUser fxUser &&
                getId().equals(fxUser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
