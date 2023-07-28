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
 * Third party persons.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(schema = "public", name = "person")
public class Person {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Type of id document.
     */
    private DoiType doiType;
    /**
     * Number of id document.
     */
    private String doiNum;
    /**
     * Person name.
     */
    private String name;
    /**
     * Active flag. If false: this entity should be treated as if deleted.
     */
    private boolean active;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenPersonId")
    @SequenceGenerator(name = "GenPersonId",
            schema = "public",
            sequenceName = "sq_person_id",
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
     * @return value of {@link #doiType}
     */
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "doi_type")
    public DoiType getDoiType() {
        return doiType;
    }

    /**
     * Public accessor - setter.
     *
     * @param doiType value to set into {@link #doiType}
     */
    public void setDoiType(DoiType doiType) {
        this.doiType = doiType;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #doiNum}
     */
    @Basic
    @Column(name = "doi_num", length = 20)
    public String getDoiNum() {
        return doiNum;
    }

    /**
     * Public accessor - setter.
     *
     * @param doiNum value to set into {@link #doiNum}
     */
    public void setDoiNum(String doiNum) {
        this.doiNum = doiNum;
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
        return this == o || o instanceof Person person &&
                getId() == person.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
