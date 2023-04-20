package com.yupay.lunatico.fxmview;

import com.yupay.lunatico.model.MovementType;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.Objects;

import static javafx.collections.FXCollections.observableArrayList;

public class FxMovement {
    /**
     * TODO: document id
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * TODO: document store
     */
    private final ObjectProperty<FxStore> store =
            new SimpleObjectProperty<>(this, "store");
    /**
     * TODO: document type
     */
    private final ObjectProperty<MovementType> type =
            new SimpleObjectProperty<>(this, "type");
    /**
     * TODO: document docDate
     */
    private final ObjectProperty<LocalDate> docDate =
            new SimpleObjectProperty<>(this, "docDate");
    /**
     * TODO: document taxDate
     */
    private final ObjectProperty<LocalDate> taxDate =
            new SimpleObjectProperty<>(this, "taxDate");
    /**
     * TODO: document party
     */
    private final ObjectProperty<FxParty> party =
            new SimpleObjectProperty<>(this, "party");
    /**
     * TODO: document folioType
     */
    private final ObjectProperty<FxFolioType> folioType =
            new SimpleObjectProperty<>(this, "folioType");
    /**
     * TODO: document folioSerie
     */
    private final StringProperty folioSerie =
            new SimpleStringProperty(this, "folioSerie");
    /**
     * TODO: document folioNumber
     */
    private final StringProperty folioNumber =
            new SimpleStringProperty(this, "folioNumber");
    /**
     * TODO: document notes
     */
    private final StringProperty notes =
            new SimpleStringProperty(this, "notes");
    /**
     * TODO: document owner
     */
    private final ObjectProperty<FxUser> owner =
            new SimpleObjectProperty<>(this, "owner");
    /**
     * TODO: document detail
     */
    private final ListProperty<FxMovementLine> detail =
            new SimpleListProperty<FxMovementLine>(this, "detail", observableArrayList());

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #id}.get();
     */
    public final long getId() {
        return id.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param id value to assign into {@link #id}.
     */
    public final void setId(long id) {
        this.id.set(id);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #id}.
     */
    public final LongProperty idProperty() {
        return id;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #store}.get();
     */
    public final FxStore getStore() {
        return store.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param store value to assign into {@link #store}.
     */
    public final void setStore(FxStore store) {
        this.store.set(store);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #store}.
     */
    public final ObjectProperty<FxStore> storeProperty() {
        return store;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #type}.get();
     */
    public final MovementType getType() {
        return type.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param type value to assign into {@link #type}.
     */
    public final void setType(MovementType type) {
        this.type.set(type);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #type}.
     */
    public final ObjectProperty<MovementType> typeProperty() {
        return type;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #docDate}.get();
     */
    public final LocalDate getDocDate() {
        return docDate.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param docDate value to assign into {@link #docDate}.
     */
    public final void setDocDate(LocalDate docDate) {
        this.docDate.set(docDate);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #docDate}.
     */
    public final ObjectProperty<LocalDate> docDateProperty() {
        return docDate;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #taxDate}.get();
     */
    public final LocalDate getTaxDate() {
        return taxDate.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param taxDate value to assign into {@link #taxDate}.
     */
    public final void setTaxDate(LocalDate taxDate) {
        this.taxDate.set(taxDate);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #taxDate}.
     */
    public final ObjectProperty<LocalDate> taxDateProperty() {
        return taxDate;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #party}.get();
     */
    public final FxParty getParty() {
        return party.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param party value to assign into {@link #party}.
     */
    public final void setParty(FxParty party) {
        this.party.set(party);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #party}.
     */
    public final ObjectProperty<FxParty> partyProperty() {
        return party;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #folioType}.get();
     */
    public final FxFolioType getFolioType() {
        return folioType.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param folioType value to assign into {@link #folioType}.
     */
    public final void setFolioType(FxFolioType folioType) {
        this.folioType.set(folioType);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #folioType}.
     */
    public final ObjectProperty<FxFolioType> folioTypeProperty() {
        return folioType;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #folioSerie}.get();
     */
    public final String getFolioSerie() {
        return folioSerie.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param folioSerie value to assign into {@link #folioSerie}.
     */
    public final void setFolioSerie(String folioSerie) {
        this.folioSerie.set(folioSerie);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #folioSerie}.
     */
    public final StringProperty folioSerieProperty() {
        return folioSerie;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #folioNumber}.get();
     */
    public final String getFolioNumber() {
        return folioNumber.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param folioNumber value to assign into {@link #folioNumber}.
     */
    public final void setFolioNumber(String folioNumber) {
        this.folioNumber.set(folioNumber);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #folioNumber}.
     */
    public final StringProperty folioNumberProperty() {
        return folioNumber;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #notes}.get();
     */
    public final String getNotes() {
        return notes.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param notes value to assign into {@link #notes}.
     */
    public final void setNotes(String notes) {
        this.notes.set(notes);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #notes}.
     */
    public final StringProperty notesProperty() {
        return notes;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #owner}.get();
     */
    public final FxUser getOwner() {
        return owner.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param owner value to assign into {@link #owner}.
     */
    public final void setOwner(FxUser owner) {
        this.owner.set(owner);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #owner}.
     */
    public final ObjectProperty<FxUser> ownerProperty() {
        return owner;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #detail}.get();
     */
    public final ObservableList<FxMovementLine> getDetail() {
        return detail.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param detail value to assign into {@link #detail}.
     */
    public final void setDetail(ObservableList<FxMovementLine> detail) {
        this.detail.set(detail);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #detail}.
     */
    public final ListProperty<FxMovementLine> detailProperty() {
        return detail;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxMovement that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
