package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entity for warehouse items.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(schema = "public", name = "item")
public class Item {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Name (description) of the item.
     */
    private String name;
    /**
     * Item type.
     */
    private ItemType type;
    /**
     * Current item balance on sale exposition.
     */
    private BigDecimal balanceOnsale;
    /**
     * Current item balance stored in warehouse.
     */
    private BigDecimal balanceStored;
    /**
     * Current item balance in units (stock).
     */
    private BigDecimal balanceUnits;
    /**
     * Current item balance unitary cost.
     */
    private BigDecimal balanceUnitCost;
    /**
     * Current item balance cost.
     */
    private BigDecimal balanceCost;
    /**
     * Item notes and remarks.
     */
    private String notes;
    /**
     * Stamp at when item was first created.
     */
    private LocalDateTime created;
    /**
     * Flag, true if the item is active, otherwise
     * it should be treated as if entity was deleted.
     */
    private boolean active;
    /**
     * The measurement unit.
     */
    private Unit unit;
    /**
     * User who created this item.
     */
    private User owner;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenItemID")
    @SequenceGenerator(name = "GenItemID",
            schema = "public",
            sequenceName = "sq_item_id",
            allocationSize = 1)
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
     * @return value of {@link #type}
     */
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public ItemType getType() {
        return type;
    }

    /**
     * Public accessor - setter.
     *
     * @param type value to set into {@link #type}
     */
    public void setType(ItemType type) {
        this.type = type;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balanceOnsale}
     */
    @Basic
    @Column(name = "balance_onsale", nullable = false, precision = 14, scale = 8)
    public BigDecimal getBalanceOnsale() {
        return balanceOnsale;
    }

    /**
     * Public accessor - setter.
     *
     * @param balanceOnsale value to set into {@link #balanceOnsale}
     */
    public void setBalanceOnsale(BigDecimal balanceOnsale) {
        this.balanceOnsale = balanceOnsale;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balanceStored}
     */
    @Basic
    @Column(name = "balance_stored", nullable = false, precision = 14, scale = 8)
    public BigDecimal getBalanceStored() {
        return balanceStored;
    }

    /**
     * Public accessor - setter.
     *
     * @param balanceStored value to set into {@link #balanceStored}
     */
    public void setBalanceStored(BigDecimal balanceStored) {
        this.balanceStored = balanceStored;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balanceUnits}
     */
    @Basic
    @Column(name = "balance_units", nullable = false, precision = 14, scale = 8)
    public BigDecimal getBalanceUnits() {
        return balanceUnits;
    }

    /**
     * Public accessor - setter.
     *
     * @param balanceUnits value to set into {@link #balanceUnits}
     */
    public void setBalanceUnits(BigDecimal balanceUnits) {
        this.balanceUnits = balanceUnits;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balanceUnitCost}
     */
    @Basic
    @Column(name = "balance_unit_cost", nullable = false, precision = 14, scale = 8)
    public BigDecimal getBalanceUnitCost() {
        return balanceUnitCost;
    }

    /**
     * Public accessor - setter.
     *
     * @param balanceUnitCost value to set into {@link #balanceUnitCost}
     */
    public void setBalanceUnitCost(BigDecimal balanceUnitCost) {
        this.balanceUnitCost = balanceUnitCost;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balanceCost}
     */
    @Basic
    @Column(name = "balance_cost", nullable = false, precision = 14, scale = 8)
    public BigDecimal getBalanceCost() {
        return balanceCost;
    }

    /**
     * Public accessor - setter.
     *
     * @param balanceCost value to set into {@link #balanceCost}
     */
    public void setBalanceCost(BigDecimal balanceCost) {
        this.balanceCost = balanceCost;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #notes}
     */
    @Basic
    @Column(name = "notes", length = -1)
    public String getNotes() {
        return notes;
    }

    /**
     * Public accessor - setter.
     *
     * @param notes value to set into {@link #notes}
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #created}
     */
    @Basic
    @Column(name = "created", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * Public accessor - setter.
     *
     * @param created value to set into {@link #created}
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
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

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #unit}
     */
    @ManyToOne
    @JoinColumn(name = "unit", referencedColumnName = "id", nullable = false)
    public Unit getUnit() {
        return unit;
    }

    /**
     * Public accessor - setter.
     *
     * @param unit value to set into {@link #unit}
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #owner}
     */
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
    public User getOwner() {
        return owner;
    }

    /**
     * Public accessor - setter.
     *
     * @param owner value to set into {@link #owner}
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Item item &&
                getId() == item.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
