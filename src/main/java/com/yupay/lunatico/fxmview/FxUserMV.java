package com.yupay.lunatico.fxmview;

import com.yupay.lunatico.model.ModelView;
import com.yupay.lunatico.model.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * JavaFX model view for Users.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUserMV extends ModelView<User, FxUserMV> {
    /**
     * The login name.
     */
    private final StringProperty id =
            new SimpleStringProperty(this, "id");
    /**
     * Real name.
     */
    private final StringProperty realName =
            new SimpleStringProperty(this, "realName");
    /**
     * Active flag.
     */
    private final BooleanProperty active =
            new SimpleBooleanProperty(this, "active");
    /**
     * Role to view information but not modify it.
     */
    private final BooleanProperty roleViewer =
            new SimpleBooleanProperty(this, "roleViewer");
    /**
     * Role for business managers.
     */
    private final BooleanProperty roleAdmin =
            new SimpleBooleanProperty(this, "roleAdmin");
    /**
     * Role for system managers (superusers).
     */
    private final BooleanProperty roleAdminSys =
            new SimpleBooleanProperty(this, "roleAdminSys");
    /**
     * Role for informants.
     */
    private final BooleanProperty roleReporter =
            new SimpleBooleanProperty(this, "roleReporter");

    /**
     * Constructor to copy information from a model entity.
     *
     * @param model the model entity.
     */
    public FxUserMV(@NotNull User model) {
        fromModel(model);
    }

    /**
     * Default no-op constructor.
     */
    public FxUserMV() {
    }

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
     * @return value of {@link #roleViewer}.get();
     */
    public final boolean isRoleViewer() {
        return roleViewer.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param roleViewer value to assign into {@link #roleViewer}.
     */
    public final void setRoleViewer(boolean roleViewer) {
        this.roleViewer.set(roleViewer);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleViewer}.
     */
    public final BooleanProperty roleViewerProperty() {
        return roleViewer;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #roleAdmin}.get();
     */
    public final boolean isRoleAdmin() {
        return roleAdmin.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param roleAdmin value to assign into {@link #roleAdmin}.
     */
    public final void setRoleAdmin(boolean roleAdmin) {
        this.roleAdmin.set(roleAdmin);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleAdmin}.
     */
    public final BooleanProperty roleAdminProperty() {
        return roleAdmin;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #roleAdminSys}.get();
     */
    public final boolean isRoleAdminSys() {
        return roleAdminSys.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param roleAdminSys value to assign into {@link #roleAdminSys}.
     */
    public final void setRoleAdminSys(boolean roleAdminSys) {
        this.roleAdminSys.set(roleAdminSys);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleAdminSys}.
     */
    public final BooleanProperty roleAdminSysProperty() {
        return roleAdminSys;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #roleReporter}.get();
     */
    public final boolean isRoleReporter() {
        return roleReporter.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param roleReporter value to assign into {@link #roleReporter}.
     */
    public final void setRoleReporter(boolean roleReporter) {
        this.roleReporter.set(roleReporter);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #roleReporter}.
     */
    public final BooleanProperty roleReporterProperty() {
        return roleReporter;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull FxUserMV deepCopy() {
        var r = new FxUserMV();
        r.setActive(isActive());
        r.setId(getId());
        r.setRealName(getRealName());
        r.setRoleAdmin(isRoleAdmin());
        r.setRoleAdminSys(isRoleAdminSys());
        r.setRoleReporter(isRoleReporter());
        r.setRoleViewer(isRoleViewer());
        return r;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull User toModel() {
        var r = new User();
        r.setActive(isActive());
        r.setId(getId());
        r.setRealName(getRealName());
        r.setRoleAdmin(isRoleAdmin());
        r.setRoleAdminSys(isRoleAdminSys());
        r.setRoleReporter(isRoleReporter());
        r.setRoleViewer(isRoleViewer());
        return r;
    }

    @Override
    @SuppressWarnings("DuplicatedCode")
    public void fromModel(@NotNull User m) {
        setActive(m.isActive());
        setId(m.getId());
        setRealName(m.getRealName());
        setRoleAdmin(m.isRoleAdmin());
        setRoleAdminSys(m.isRoleAdminSys());
        setRoleReporter(m.isRoleReporter());
        setRoleViewer(m.isRoleViewer());
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxUserMV fxUserMV &&
                getId().equals(fxUserMV.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
