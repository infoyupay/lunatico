package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The system's users entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(name = "mmq_user", schema = "public")
public class User {
    /**
     * The user ID (login name).
     */
    private String id;
    /**
     * The user real name.
     */
    private String realName;
    /**
     * The user has viewer role.
     */
    private boolean roleViewer;
    /**
     * The user has administrator role.
     */
    private boolean roleAdmin;
    /**
     * The user has system administrator role.
     */
    private boolean roleAdminSys;
    /**
     * The user has reporter role.
     */
    private boolean roleReporter;
    /**
     * The user is currently active.
     */
    private boolean active;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @Id
    @Column(name = "id", nullable = false, length = -1)
    public String getId() {
        return id;
    }

    /**
     * Public accessor - setter.
     *
     * @param id value to set into {@link #id}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #realName}
     */
    @Basic
    @Column(name = "real_name", nullable = false, length = -1)
    public String getRealName() {
        return realName;
    }

    /**
     * Public accessor - setter.
     *
     * @param realName value to set into {@link #realName}
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #roleViewer}
     */
    @Basic
    @Column(name = "role_viewer", nullable = false)
    public boolean isRoleViewer() {
        return roleViewer;
    }

    /**
     * Public accessor - setter.
     *
     * @param roleViewer value to set into {@link #roleViewer}
     */
    public void setRoleViewer(boolean roleViewer) {
        this.roleViewer = roleViewer;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #roleAdmin}
     */
    @Basic
    @Column(name = "role_admin", nullable = false)
    public boolean isRoleAdmin() {
        return roleAdmin;
    }

    /**
     * Public accessor - setter.
     *
     * @param roleAdmin value to set into {@link #roleAdmin}
     */
    public void setRoleAdmin(boolean roleAdmin) {
        this.roleAdmin = roleAdmin;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #roleAdminSys}
     */
    @Basic
    @Column(name = "role_admin_sys", nullable = false)
    public boolean isRoleAdminSys() {
        return roleAdminSys;
    }

    /**
     * Public accessor - setter.
     *
     * @param roleAdminSys value to set into {@link #roleAdminSys}
     */
    public void setRoleAdminSys(boolean roleAdminSys) {
        this.roleAdminSys = roleAdminSys;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #roleReporter}
     */
    @Basic
    @Column(name = "role_reporter", nullable = false)
    public boolean isRoleReporter() {
        return roleReporter;
    }

    /**
     * Public accessor - setter.
     *
     * @param roleReporter value to set into {@link #roleReporter}
     */
    public void setRoleReporter(boolean roleReporter) {
        this.roleReporter = roleReporter;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #active}
     */
    @Basic
    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return active;
    }

    /**
     * Public accessor - setter.
     *
     * @param active value to set into {@link #active}
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof User user &&
                Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
