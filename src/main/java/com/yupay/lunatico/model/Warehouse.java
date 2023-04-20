package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The warehouse entity.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(schema = "public", name = "warehouse")
public class Warehouse {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Warehouse name (title).
     */
    private String name;
    /**
     * If this is a virtual warehouse, state virtual type.
     */
    private VirtualWarehouseType virtualType;
    /**
     * Active flag, true if warehouse may accept movements.
     */
    private boolean active;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenWarehouseID")
    @SequenceGenerator(name = "GenWarehouseID",
            schema = "public",
            sequenceName = "sq_warehouse_id")
    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    /**
     * Public accessor - setter.
     *
     * @param id value to set into {@link #id}
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #name}
     */
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    /**
     * Public accessor - setter.
     *
     * @param name value to set into {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #virtualType}
     */
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "virtual_type")
    public VirtualWarehouseType getVirtualType() {
        return virtualType;
    }

    /**
     * Public accessor - setter.
     *
     * @param virtualType value to set into {@link #virtualType}
     */
    public void setVirtualType(VirtualWarehouseType virtualType) {
        this.virtualType = virtualType;
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
        return this == o || o instanceof Warehouse warehouse &&
                getId() == warehouse.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
