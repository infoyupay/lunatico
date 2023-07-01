package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * The daily balance of the items.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(schema = "public", name = "balance")
public class Balance {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Date of generation.
     */
    private LocalDateTime shotStamp;
    /**
     * Type of the balance.
     */
    private BalanceType type;
    /**
     * Stock balance (in units).
     */
    private BigDecimal balanceUnits;
    /**
     * Stock balance's unitary cost.
     */
    private BigDecimal balanceUnitCost;
    /**
     * Stock balance's cost (multiply {@link #balanceUnits} * {@link #balanceUnitCost})
     */
    private BigDecimal balanceCost;
    /**
     * Warehouse at where the balance refers.
     */
    private Warehouse warehouse;
    /**
     * Item linked to this balance.
     */
    private Item item;
    /**
     * User who generated the balance.
     */
    private User user;
    /**
     * Balance summary details.
     */
    private Collection<BalanceDetail> detail;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenBalanceID")
    @SequenceGenerator(name = "GenBalanceID",
            schema = "public",
            sequenceName = "sq_balance_id",
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
     * @return value of {@link #shotStamp}
     */
    @Basic
    @Column(name = "shot_stamp", nullable = false,
            updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime getShotStamp() {
        return shotStamp;
    }

    /**
     * Public accessor - setter.
     *
     * @param date value to set into {@link #shotStamp}
     */
    public void setShotStamp(LocalDateTime date) {
        this.shotStamp = date;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #type}
     */
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    public BalanceType getType() {
        return type;
    }

    /**
     * Public accessor - setter.
     *
     * @param type value to set into {@link #type}
     */
    public void setType(BalanceType type) {
        this.type = type;
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
     * @return value of {@link #warehouse}
     */
    @ManyToOne
    @JoinColumn(name = "warehouse", referencedColumnName = "id")
    public Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Public accessor - setter.
     *
     * @param warehouse value to set into {@link #warehouse}
     */
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #item}
     */
    @ManyToOne
    @JoinColumn(name = "item", referencedColumnName = "id", nullable = false)
    public Item getItem() {
        return item;
    }

    /**
     * Public accessor - setter.
     *
     * @param item value to set into {@link #item}
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #user}
     */
    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id", nullable = false)
    public User getUser() {
        return user;
    }

    /**
     * Public accessor - setter.
     *
     * @param user value to set into {@link #user}
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #detail}
     */
    @OneToMany(mappedBy = "balance", cascade = CascadeType.ALL)
    public Collection<BalanceDetail> getDetail() {
        return detail;
    }

    /**
     * Public accessor - setter.
     *
     * @param detail value to set into {@link #detail}
     */
    public void setDetail(Collection<BalanceDetail> detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Balance balance &&
                getId() == balance.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
