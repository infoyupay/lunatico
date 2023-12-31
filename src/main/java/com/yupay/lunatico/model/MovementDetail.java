/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Line of detail of each movement.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(name = "movement_detail", schema = "public")
public class MovementDetail {
    /**
     * Autogenerated ID.
     */
    private long id;
    /**
     * Line number.
     */
    private short line;
    /**
     * Moved quantity.
     */
    private BigDecimal quantity = BigDecimal.ZERO;
    /**
     * Quantity balance before movement.
     */
    private BigDecimal beforeQuantity = BigDecimal.ZERO;
    /**
     * Unitary pricing before movement.
     */
    private BigDecimal beforePrice = BigDecimal.ZERO;
    /**
     * Line cost before movement.
     */
    private BigDecimal beforeCost = BigDecimal.ZERO;
    /**
     * Input quantity.
     */
    private BigDecimal inQuantity = BigDecimal.ZERO;
    /**
     * Input unit pricing.
     */
    private BigDecimal inPrice = BigDecimal.ZERO;
    /**
     * Input total cost.
     */
    private BigDecimal inCost = BigDecimal.ZERO;
    /**
     * Output quantity.
     */
    private BigDecimal outQuantity = BigDecimal.ZERO;
    /**
     * Output unitary pricing.
     */
    private BigDecimal outPrice = BigDecimal.ZERO;
    /**
     * Output total cost.
     */
    private BigDecimal outCost = BigDecimal.ZERO;
    /**
     * Items quantity balance after movement.
     */
    private BigDecimal balanceQuantity = BigDecimal.ZERO;
    /**
     * Balance unit pricing after movement.
     */
    private BigDecimal balancePrice = BigDecimal.ZERO;
    /**
     * Balance cost amount.
     */
    private BigDecimal balanceCost = BigDecimal.ZERO;
    /**
     * Referential unit price, when set by user.
     */
    private BigDecimal priceRef;
    /**
     * Owner movement.
     */
    private Movement movement;
    /**
     * Moved item.
     */
    private Item item;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenMovementDetailID")
    @SequenceGenerator(name = "GenMovementDetailID",
            schema = "public",
            sequenceName = "sq_movement_detail_id",
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
     * @return value of {@link #line}
     */
    @Basic
    @Column(name = "line", nullable = false)
    public short getLine() {
        return line;
    }

    /**
     * Public accessor - setter.
     *
     * @param line value to set into {@link #line}
     */
    public void setLine(short line) {
        this.line = line;
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
     * @return value of {@link #beforeQuantity}
     */
    @Basic
    @Column(name = "before_quantity", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "before_price", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "before_cost", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "in_quantity", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "in_price", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "in_cost", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "out_quantity", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "out_price", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "out_cost", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "balance_quantity", nullable = false, precision = 14, scale = 8)
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
    @Column(name = "balance_price", nullable = false, precision = 14, scale = 8)
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
     * @return value of {@link #movement}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movement", referencedColumnName = "id", nullable = false)
    public Movement getMovement() {
        return movement;
    }

    /**
     * Public accessor - setter.
     *
     * @param movement value to set into {@link #movement}
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
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
     * @return value of {@link #priceRef}
     */
    @Basic
    @Column(name = "price_ref", precision = 14, scale = 8)
    public BigDecimal getPriceRef() {
        return priceRef;
    }

    /**
     * Public accessor - setter.
     *
     * @param priceRef value to set into {@link #priceRef}
     */
    public void setPriceRef(BigDecimal priceRef) {
        this.priceRef = priceRef;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof MovementDetail that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

