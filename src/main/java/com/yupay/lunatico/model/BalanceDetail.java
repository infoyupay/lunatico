package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Detail lines of a balance.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(name = "balance_detail", schema = "public")
public class BalanceDetail {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Type of summarized movement.
     */
    private MovementType summaryType;
    /**
     * Balance units quantity.
     */
    private BigDecimal quantity;
    /**
     * Balance cost amount.
     */
    private BigDecimal cost;
    /**
     * Owner balance.
     */
    private Balance balance;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenBalanceDetailID")
    @SequenceGenerator(name = "GenBalanceDetailID",
            schema = "public",
            sequenceName = "sq_balance_detail_id",
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
     * @return value of {@link #summaryType}
     */
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "summary_type", nullable = false)
    public MovementType getSummaryType() {
        return summaryType;
    }

    /**
     * Public accessor - setter.
     *
     * @param summaryType value to set into {@link #summaryType}
     */
    public void setSummaryType(MovementType summaryType) {
        this.summaryType = summaryType;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #quantity}
     */
    @Basic
    @Column(name = "quantity", nullable = false, precision = 14, scale = 8)
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * Public accessor - setter.
     *
     * @param quantity value to set into {@link #quantity}
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #cost}
     */
    @Basic
    @Column(name = "cost", nullable = false, precision = 14, scale = 8)
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Public accessor - setter.
     *
     * @param cost value to set into {@link #cost}
     */
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balance}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "balance", referencedColumnName = "id", nullable = false)
    public Balance getBalance() {
        return balance;
    }

    /**
     * Public accessor - setter.
     *
     * @param balance value to set into {@link #balance}
     */
    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof BalanceDetail that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
