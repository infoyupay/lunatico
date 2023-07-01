package com.yupay.lunatico.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

/**
 * Movements of items at warehouse entity.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
@Entity
@Table(schema = "public", name = "movement")
public class Movement {
    /**
     * Autoincremental ID.
     */
    private long id;
    /**
     * Type of movement.
     */
    private MovementType type;
    /**
     * Date of movement.
     */
    private LocalDate docDate;
    /**
     * Date at registration instant.
     */
    private LocalDateTime ownDate;
    /**
     * Folio series.
     */
    private String folioSerie;
    /**
     * Folio number.
     */
    private String folioNumber;
    /**
     * Notes and remarks about this movement.
     */
    private String notes;
    /**
     * Warehouse at where this movement was made.
     */
    private Warehouse warehouse;
    /**
     * Third party person.
     */
    private Person person;
    /**
     * Type of folio.
     */
    private TypeFolio folioType;
    /**
     * Movement owner.
     */
    private User owner;
    /**
     * Details of movement.
     */
    private Collection<MovementDetail> detail;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #id}
     */
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GenMovementID")
    @SequenceGenerator(name = "GenMovementID",
            schema = "public",
            sequenceName = "sq_movement_id",
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
     * @return value of {@link #type}
     */
    @Basic
    @Column(name = "type", nullable = false)
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
     * @return value of {@link #docDate}
     */
    @Basic
    @Column(name = "doc_date", nullable = false,
            columnDefinition = "DATE")
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
     * @return value of {@link #ownDate}
     */
    @Basic
    @Column(name = "own_date", nullable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false, updatable = false)
    public LocalDateTime getOwnDate() {
        return ownDate;
    }

    /**
     * Public accessor - setter.
     *
     * @param ownDate value to set into {@link #ownDate}
     */
    public void setOwnDate(LocalDateTime ownDate) {
        this.ownDate = ownDate;
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
     * @return value of {@link #warehouse}
     */
    @ManyToOne
    @JoinColumn(name = "warehouse", referencedColumnName = "id", nullable = false)
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
     * @return value of {@link #person}
     */
    @ManyToOne
    @JoinColumn(name = "person", referencedColumnName = "id")
    public Person getPerson() {
        return person;
    }

    /**
     * Public accessor - setter.
     *
     * @param person value to set into {@link #person}
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #folioType}
     */
    @ManyToOne
    @JoinColumn(name = "folio_type", referencedColumnName = "id")
    public TypeFolio getFolioType() {
        return folioType;
    }

    /**
     * Public accessor - setter.
     *
     * @param folioType value to set into {@link #folioType}
     */
    public void setFolioType(TypeFolio folioType) {
        this.folioType = folioType;
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

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #detail}
     */
    @OneToMany(mappedBy = "movement", cascade = CascadeType.ALL)
    public Collection<MovementDetail> getDetail() {
        return detail;
    }

    /**
     * Public accessor - setter.
     *
     * @param detail value to set into {@link #detail}
     */
    public void setDetail(Collection<MovementDetail> detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Movement movement &&
                getId() == movement.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
