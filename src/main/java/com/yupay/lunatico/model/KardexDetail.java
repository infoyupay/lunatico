package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The kardex detail as a light view of movements.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(name = "kardex_detail", schema = "public")
public class KardexDetail {
    /**
     * The detail ID number.
     */
    private long id;
    /**
     * The movement id. This is useful
     * to retrieve the whole movement.
     */
    private long movement;
    /**
     * The item id. This is useful
     * for filtering.
     */
    private long item;
    /**
     * The movement, user's declaration date.
     */
    private LocalDate docDate;
    /**
     * Type of movement.
     */
    private MovementType type;
    /**
     * Warehouse at which the movement took place.
     */
    private long warehouse;
    /**
     * The folio type ID.
     */
    private long folioType;
    /**
     * The folio type name.
     */
    private String folioTypeName;
    /**
     * The folio series.
     */
    private String folioSerie;
    /**
     * The folio number.
     */
    private String folioNumber;
    /**
     * Quantity (stock) before movement.
     */
    private BigDecimal beforeQuantity;
    /**
     * Unit price (before) movement.
     */
    private BigDecimal beforePrice;
    /**
     * Stock cost before movement.
     */
    private BigDecimal beforeCost;
    /**
     * Input / entrance quantity.
     */
    private BigDecimal inQuantity;
    /**
     * Input / entrance unit price.
     */
    private BigDecimal inPrice;
    /**
     * Input / entrance cost.
     */
    private BigDecimal inCost;
    /**
     * Output quantity.
     */
    private BigDecimal outQuantity;
    /**
     * Output unit price.
     */
    private BigDecimal outPrice;
    /**
     * Output total cost.
     */
    private BigDecimal outCost;
    /**
     * End Balance quantity.
     */
    private BigDecimal balanceQuantity;
    /**
     * End balance unit price.
     */
    private BigDecimal balancePrice;
    /**
     * End balance cost.
     */
    private BigDecimal balanceCost;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @Id
    @Column(name = "id")
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
     * @return value of {@link #movement}
     */
    @Basic
    @Column(name = "movement")
    public long getMovement() {
        return movement;
    }

    /**
     * Public accessor - setter.
     *
     * @param movement value to set into {@link #movement}
     */
    public void setMovement(long movement) {
        this.movement = movement;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #item}
     */
    @Basic
    @Column(name = "item")
    public long getItem() {
        return item;
    }

    /**
     * Public accessor - setter.
     *
     * @param item value to set into {@link #item}
     */
    public void setItem(long item) {
        this.item = item;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #docDate}
     */
    @Basic
    @Column(name = "doc_date", columnDefinition = "DATE")
    public LocalDate getDocDate() {
        return docDate;
    }

    /**
     * Public accessor - setter.
     *
     * @param docDate value to set into {@link #docDate}
     */
    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #type}
     */
    @Basic
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public MovementType getType() {
        return type;
    }

    /**
     * Public accessor - setter.
     *
     * @param type value to set into {@link #type}
     */
    public void setType(MovementType type) {
        this.type = type;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #warehouse}
     */
    @Basic
    @Column(name = "warehouse")
    public long getWarehouse() {
        return warehouse;
    }

    /**
     * Public accessor - setter.
     *
     * @param warehouse value to set into {@link #warehouse}
     */
    public void setWarehouse(long warehouse) {
        this.warehouse = warehouse;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #folioType}
     */
    @Basic
    @Column(name = "folio_type")
    public long getFolioType() {
        return folioType;
    }

    /**
     * Public accessor - setter.
     *
     * @param folioType value to set into {@link #folioType}
     */
    public void setFolioType(long folioType) {
        this.folioType = folioType;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #folioTypeName}
     */
    @Basic
    @Column(name = "folio_type_name", length = -1)
    public String getFolioTypeName() {
        return folioTypeName;
    }

    /**
     * Public accessor - setter.
     *
     * @param folioTypeName value to set into {@link #folioTypeName}
     */
    public void setFolioTypeName(String folioTypeName) {
        this.folioTypeName = folioTypeName;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #folioSerie}
     */
    @Basic
    @Column(name = "folio_serie", length = 20)
    public String getFolioSerie() {
        return folioSerie;
    }

    /**
     * Public accessor - setter.
     *
     * @param folioSerie value to set into {@link #folioSerie}
     */
    public void setFolioSerie(String folioSerie) {
        this.folioSerie = folioSerie;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #folioNumber}
     */
    @Basic
    @Column(name = "folio_number", length = 20)
    public String getFolioNumber() {
        return folioNumber;
    }

    /**
     * Public accessor - setter.
     *
     * @param folioNumber value to set into {@link #folioNumber}
     */
    public void setFolioNumber(String folioNumber) {
        this.folioNumber = folioNumber;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #beforeQuantity}
     */
    @Basic
    @Column(name = "before_quantity", precision = 8)
    public BigDecimal getBeforeQuantity() {
        return beforeQuantity;
    }

    /**
     * Public accessor - setter.
     *
     * @param beforeQuantity value to set into {@link #beforeQuantity}
     */
    public void setBeforeQuantity(BigDecimal beforeQuantity) {
        this.beforeQuantity = beforeQuantity;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #beforePrice}
     */
    @Basic
    @Column(name = "before_price", precision = 8)
    public BigDecimal getBeforePrice() {
        return beforePrice;
    }

    /**
     * Public accessor - setter.
     *
     * @param beforePrice value to set into {@link #beforePrice}
     */
    public void setBeforePrice(BigDecimal beforePrice) {
        this.beforePrice = beforePrice;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #beforeCost}
     */
    @Basic
    @Column(name = "before_cost", precision = 8)
    public BigDecimal getBeforeCost() {
        return beforeCost;
    }

    /**
     * Public accessor - setter.
     *
     * @param beforeCost value to set into {@link #beforeCost}
     */
    public void setBeforeCost(BigDecimal beforeCost) {
        this.beforeCost = beforeCost;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #inQuantity}
     */
    @Basic
    @Column(name = "in_quantity", precision = 8)
    public BigDecimal getInQuantity() {
        return inQuantity;
    }

    /**
     * Public accessor - setter.
     *
     * @param inQuantity value to set into {@link #inQuantity}
     */
    public void setInQuantity(BigDecimal inQuantity) {
        this.inQuantity = inQuantity;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #inPrice}
     */
    @Basic
    @Column(name = "in_price", precision = 8)
    public BigDecimal getInPrice() {
        return inPrice;
    }

    /**
     * Public accessor - setter.
     *
     * @param inPrice value to set into {@link #inPrice}
     */
    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #inCost}
     */
    @Basic
    @Column(name = "in_cost", precision = 8)
    public BigDecimal getInCost() {
        return inCost;
    }

    /**
     * Public accessor - setter.
     *
     * @param inCost value to set into {@link #inCost}
     */
    public void setInCost(BigDecimal inCost) {
        this.inCost = inCost;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #outQuantity}
     */
    @Basic
    @Column(name = "out_quantity", precision = 8)
    public BigDecimal getOutQuantity() {
        return outQuantity;
    }

    /**
     * Public accessor - setter.
     *
     * @param outQuantity value to set into {@link #outQuantity}
     */
    public void setOutQuantity(BigDecimal outQuantity) {
        this.outQuantity = outQuantity;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #outPrice}
     */
    @Basic
    @Column(name = "out_price", precision = 8)
    public BigDecimal getOutPrice() {
        return outPrice;
    }

    /**
     * Public accessor - setter.
     *
     * @param outPrice value to set into {@link #outPrice}
     */
    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #outCost}
     */
    @Basic
    @Column(name = "out_cost", precision = 8)
    public BigDecimal getOutCost() {
        return outCost;
    }

    /**
     * Public accessor - setter.
     *
     * @param outCost value to set into {@link #outCost}
     */
    public void setOutCost(BigDecimal outCost) {
        this.outCost = outCost;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balanceQuantity}
     */
    @Basic
    @Column(name = "balance_quantity", precision = 8)
    public BigDecimal getBalanceQuantity() {
        return balanceQuantity;
    }

    /**
     * Public accessor - setter.
     *
     * @param balanceQuantity value to set into {@link #balanceQuantity}
     */
    public void setBalanceQuantity(BigDecimal balanceQuantity) {
        this.balanceQuantity = balanceQuantity;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balancePrice}
     */
    @Basic
    @Column(name = "balance_price", precision = 8)
    public BigDecimal getBalancePrice() {
        return balancePrice;
    }

    /**
     * Public accessor - setter.
     *
     * @param balancePrice value to set into {@link #balancePrice}
     */
    public void setBalancePrice(BigDecimal balancePrice) {
        this.balancePrice = balancePrice;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #balanceCost}
     */
    @Basic
    @Column(name = "balance_cost", precision = 8)
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

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof KardexDetail that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}