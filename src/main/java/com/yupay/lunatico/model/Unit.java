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

import java.util.Objects;

/**
 * Measurement units.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(schema = "public", name = "unit")
public class Unit {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Tag to easily find it.
     */
    private String tag;
    /**
     * Symbol.
     */
    private String symbol;
    /**
     * Active flag, if false the unit has been deleted.
     * But it can't be physically deleted from database
     * due relationships.
     */
    private boolean active;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenUnitID")
    @SequenceGenerator(name = "GenUnitID",
            schema = "public",
            sequenceName = "sq_unit_id",
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
     * @return value of {@link #tag}
     */
    @Basic
    @Column(name = "tag", nullable = false, length = -1)
    public String getTag() {
        return tag;
    }

    /**
     * Public accessor - setter.
     *
     * @param tag value to set into {@link #tag}
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #symbol}
     */
    @Basic
    @Column(name = "symbol", nullable = false, length = -1)
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
        return this == o || o instanceof Unit unit &&
                getId() == unit.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
