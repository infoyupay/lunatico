package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Type of Folio entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(name = "type_folio", schema = "public")
public class TypeFolio {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Name (title) of this type of folio.
     */
    private String name;
    /**
     * Active flag.
     */
    private boolean active;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenTypeFolioID")
    @SequenceGenerator(name = "GenTypeFolioID",
            schema = "public",
            sequenceName = "sq_type_folio_id",
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
        return this == o || o instanceof TypeFolio typeFolio &&
                getId() == typeFolio.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
