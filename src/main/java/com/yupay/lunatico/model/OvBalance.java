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
 * This is the overview balance entity, this
 * is a lightweight view object to get the current
 * local balance for each item at each warehouse.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(name = "ov_balance", schema = "public")
public class OvBalance {
    /**
     * The balance unique ID.
     */
    private long id;
    /**
     * The item ID.
     */
    private long itemId;
    /**
     * The item's Name.
     */
    private String name;
    /**
     * The type of item.
     */
    private ItemType type;
    /**
     * The measurement unit symbol.
     */
    private String symbol;
    /**
     * Warehouse's id.
     */
    private long warehouse;
    /**
     * Local Balance in units.
     */
    private BigDecimal balanceUnits;
    /**
     * Local Balance's unit price/cost.
     */
    private BigDecimal balanceUnitCost;
    /**
     * Local balance in cost.
     */
    private BigDecimal balanceCost;
    /**
     * Warehouse's name.
     */
    private String warehouseName;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #itemId}
     */
    @Basic
    @Column(name = "item_id")
    public long getItemId() {
        return itemId;
    }

    /**
     * Public accessor - setter.
     *
     * @param itemId value to set into {@link #itemId}
     */
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @Basic
    @Column(name = "id")
    @Id
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
    @Column(name = "name", length = -1)
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
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
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
     * @return value of {@link #symbol}
     */
    @Basic
    @Column(name = "symbol", length = -1)
    public String getSymbol() {
        return symbol;
    }

    /**
     * Public accessor - setter.
     *
     * @param symbol value to set into {@link #symbol}
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
     * @return value of {@link #balanceUnits}
     */
    @Basic
    @Column(name = "balance_units", precision = 8)
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
    @Column(name = "balance_unit_cost", precision = 8)
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

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #warehouseName}
     */
    @Basic
    @Column(name = "warehouse_name")
    public String getWarehouseName() {
        return warehouseName;
    }

    /**
     * Public accessor - setter.
     *
     * @param warehouseName value to set into {@link #warehouseName}
     */
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof OvBalance ovBalance &&
                getId() == ovBalance.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
