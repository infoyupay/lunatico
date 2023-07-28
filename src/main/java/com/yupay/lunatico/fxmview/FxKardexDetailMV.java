/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxmview;

import com.yupay.lunatico.model.KardexDetail;
import com.yupay.lunatico.model.ModelView;
import com.yupay.lunatico.model.MovementType;
import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * JavaFX Model view for Kardex Details.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxKardexDetailMV
        extends ModelView<KardexDetail, FxKardexDetailMV> {
    /**
     * The movement detail uinque global ID.
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * The balance cost.
     */
    private final ObjectProperty<BigDecimal> balanceCost =
            new SimpleObjectProperty<>(this, "balanceCost", new BigDecimal("0.00000000"));
    /**
     * The balance price.
     */
    private final ObjectProperty<BigDecimal> balancePrice =
            new SimpleObjectProperty<>(this, "balancePrice", new BigDecimal("0.00000000"));
    /**
     * The balance quantity.
     */
    private final ObjectProperty<BigDecimal> balanceQuantity =
            new SimpleObjectProperty<>(this, "balanceQuantity", new BigDecimal("0.00000000"));
    /**
     * Before movement cost.
     */
    private final ObjectProperty<BigDecimal> beforeCost =
            new SimpleObjectProperty<>(this, "beforeCost", new BigDecimal("0.00000000"));
    /**
     * Before movement price.
     */
    private final ObjectProperty<BigDecimal> beforePrice =
            new SimpleObjectProperty<>(this, "beforePrice", new BigDecimal("0.00000000"));
    /**
     * Before movement quantity.
     */
    private final ObjectProperty<BigDecimal> beforeQuantity =
            new SimpleObjectProperty<>(this, "beforeQuantity", new BigDecimal("0.00000000"));
    /**
     * Movement document date.
     */
    private final ObjectProperty<LocalDate> docDate =
            new SimpleObjectProperty<>(this, "docDate");
    /**
     * Movement folio number.
     */
    private final StringProperty folioNumber =
            new SimpleStringProperty(this, "folioNumber");
    /**
     * Movement folio serie.
     */
    private final StringProperty folioSerie =
            new SimpleStringProperty(this, "folioSerie");
    /**
     * Movement folio type.
     */
    private final LongProperty folioType =
            new SimpleLongProperty(this, "folioType");
    /**
     * Movement folio type's name.
     */
    private final StringProperty folioTypeName =
            new SimpleStringProperty(this, "folioTypeName");
    /**
     * Movement input cost.
     */
    private final ObjectProperty<BigDecimal> inCost =
            new SimpleObjectProperty<>(this, "inCost", new BigDecimal("0.00000000"));
    /**
     * Movement input unit price.
     */
    private final ObjectProperty<BigDecimal> inPrice =
            new SimpleObjectProperty<>(this, "inPrice", new BigDecimal("0.00000000"));
    /**
     * Movement input quantity.
     */
    private final ObjectProperty<BigDecimal> inQuantity =
            new SimpleObjectProperty<>(this, "inQuantity", new BigDecimal("0.00000000"));
    /**
     * Item's id.
     */
    private final LongProperty item =
            new SimpleLongProperty(this, "item");
    /**
     * Movement's id.
     */
    private final LongProperty movement =
            new SimpleLongProperty(this, "movement");
    /**
     * Output cost.
     */
    private final ObjectProperty<BigDecimal> outCost =
            new SimpleObjectProperty<>(this, "outCost", new BigDecimal("0.00000000"));
    /**
     * Output unit price.
     */
    private final ObjectProperty<BigDecimal> outPrice =
            new SimpleObjectProperty<>(this, "outPrice", new BigDecimal("0.00000000"));
    /**
     * Output quantity.
     */
    private final ObjectProperty<BigDecimal> outQuantity =
            new SimpleObjectProperty<>(this, "outQuantity", new BigDecimal("0.00000000"));
    /**
     * Movement type.
     */
    private final ObjectProperty<MovementType> type =
            new SimpleObjectProperty<>(this, "type");
    /**
     * Warehouse ID.
     */
    private final LongProperty warehouse =
            new SimpleLongProperty(this, "warehouse");

    /**
     * Constructor to copy information from a model entity.
     *
     * @param model the model entity.
     */
    public FxKardexDetailMV(@NotNull KardexDetail model) {
        fromModel(model);
    }

    /**
     * Default no-op constructor.
     */
    public FxKardexDetailMV() {
    }

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
     * @return value of {@link #balanceCost}.get();
     */
    public final BigDecimal getBalanceCost() {
        return balanceCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param balanceCost value to assign into {@link #balanceCost}.
     */
    public final void setBalanceCost(BigDecimal balanceCost) {
        this.balanceCost.set(balanceCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #balanceCost}.
     */
    public final ObjectProperty<BigDecimal> balanceCostProperty() {
        return balanceCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #balancePrice}.get();
     */
    public final BigDecimal getBalancePrice() {
        return balancePrice.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param balancePrice value to assign into {@link #balancePrice}.
     */
    public final void setBalancePrice(BigDecimal balancePrice) {
        this.balancePrice.set(balancePrice);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #balancePrice}.
     */
    public final ObjectProperty<BigDecimal> balancePriceProperty() {
        return balancePrice;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #balanceQuantity}.get();
     */
    public final BigDecimal getBalanceQuantity() {
        return balanceQuantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param balanceQuantity value to assign into {@link #balanceQuantity}.
     */
    public final void setBalanceQuantity(BigDecimal balanceQuantity) {
        this.balanceQuantity.set(balanceQuantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #balanceQuantity}.
     */
    public final ObjectProperty<BigDecimal> balanceQuantityProperty() {
        return balanceQuantity;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #beforeCost}.get();
     */
    public final BigDecimal getBeforeCost() {
        return beforeCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param beforeCost value to assign into {@link #beforeCost}.
     */
    public final void setBeforeCost(BigDecimal beforeCost) {
        this.beforeCost.set(beforeCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #beforeCost}.
     */
    public final ObjectProperty<BigDecimal> beforeCostProperty() {
        return beforeCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #beforePrice}.get();
     */
    public final BigDecimal getBeforePrice() {
        return beforePrice.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param beforePrice value to assign into {@link #beforePrice}.
     */
    public final void setBeforePrice(BigDecimal beforePrice) {
        this.beforePrice.set(beforePrice);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #beforePrice}.
     */
    public final ObjectProperty<BigDecimal> beforePriceProperty() {
        return beforePrice;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #beforeQuantity}.get();
     */
    public final BigDecimal getBeforeQuantity() {
        return beforeQuantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param beforeQuantity value to assign into {@link #beforeQuantity}.
     */
    public final void setBeforeQuantity(BigDecimal beforeQuantity) {
        this.beforeQuantity.set(beforeQuantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #beforeQuantity}.
     */
    public final ObjectProperty<BigDecimal> beforeQuantityProperty() {
        return beforeQuantity;
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
     * @return value of {@link #folioType}.get();
     */
    public final long getFolioType() {
        return folioType.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param folioType value to assign into {@link #folioType}.
     */
    public final void setFolioType(long folioType) {
        this.folioType.set(folioType);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #folioType}.
     */
    @SuppressWarnings("unused")
    public final LongProperty folioTypeProperty() {
        return folioType;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #folioTypeName}.get();
     */
    public final String getFolioTypeName() {
        return folioTypeName.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param folioTypeName value to assign into {@link #folioTypeName}.
     */
    public final void setFolioTypeName(String folioTypeName) {
        this.folioTypeName.set(folioTypeName);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #folioTypeName}.
     */
    public final StringProperty folioTypeNameProperty() {
        return folioTypeName;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #inCost}.get();
     */
    public final BigDecimal getInCost() {
        return inCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param inCost value to assign into {@link #inCost}.
     */
    public final void setInCost(BigDecimal inCost) {
        this.inCost.set(inCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #inCost}.
     */
    public final ObjectProperty<BigDecimal> inCostProperty() {
        return inCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #inPrice}.get();
     */
    public final BigDecimal getInPrice() {
        return inPrice.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param inPrice value to assign into {@link #inPrice}.
     */
    public final void setInPrice(BigDecimal inPrice) {
        this.inPrice.set(inPrice);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #inPrice}.
     */
    public final ObjectProperty<BigDecimal> inPriceProperty() {
        return inPrice;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #inQuantity}.get();
     */
    public final BigDecimal getInQuantity() {
        return inQuantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param inQuantity value to assign into {@link #inQuantity}.
     */
    public final void setInQuantity(BigDecimal inQuantity) {
        this.inQuantity.set(inQuantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #inQuantity}.
     */
    public final ObjectProperty<BigDecimal> inQuantityProperty() {
        return inQuantity;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #item}.get();
     */
    public final long getItem() {
        return item.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param item value to assign into {@link #item}.
     */
    public final void setItem(long item) {
        this.item.set(item);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #item}.
     */
    @SuppressWarnings("unused")
    public final LongProperty itemProperty() {
        return item;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #movement}.get();
     */
    public final long getMovement() {
        return movement.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param movement value to assign into {@link #movement}.
     */
    public final void setMovement(long movement) {
        this.movement.set(movement);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #movement}.
     */
    @SuppressWarnings("unused")
    public final LongProperty movementProperty() {
        return movement;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #outCost}.get();
     */
    public final BigDecimal getOutCost() {
        return outCost.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param outCost value to assign into {@link #outCost}.
     */
    public final void setOutCost(BigDecimal outCost) {
        this.outCost.set(outCost);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #outCost}.
     */
    public final ObjectProperty<BigDecimal> outCostProperty() {
        return outCost;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #outPrice}.get();
     */
    public final BigDecimal getOutPrice() {
        return outPrice.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param outPrice value to assign into {@link #outPrice}.
     */
    public final void setOutPrice(BigDecimal outPrice) {
        this.outPrice.set(outPrice);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #outPrice}.
     */
    public final ObjectProperty<BigDecimal> outPriceProperty() {
        return outPrice;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #outQuantity}.get();
     */
    public final BigDecimal getOutQuantity() {
        return outQuantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param outQuantity value to assign into {@link #outQuantity}.
     */
    public final void setOutQuantity(BigDecimal outQuantity) {
        this.outQuantity.set(outQuantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #outQuantity}.
     */
    public final ObjectProperty<BigDecimal> outQuantityProperty() {
        return outQuantity;
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
     * @return value of {@link #warehouse}.get();
     */
    public final long getWarehouse() {
        return warehouse.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param warehouse value to assign into {@link #warehouse}.
     */
    public final void setWarehouse(long warehouse) {
        this.warehouse.set(warehouse);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #warehouse}.
     */
    @SuppressWarnings("unused")
    public final LongProperty warehouseProperty() {
        return warehouse;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxKardexDetailMV that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull FxKardexDetailMV deepCopy() {
        var r = new FxKardexDetailMV();
        r.setBalanceCost(getBalanceCost());
        r.setBalancePrice(getBalancePrice());
        r.setBalanceQuantity(getBalanceQuantity());
        r.setBeforeCost(getBeforeCost());
        r.setBeforePrice(getBeforePrice());
        r.setBeforeQuantity(getBeforeQuantity());
        r.setDocDate(getDocDate());
        r.setFolioNumber(getFolioNumber());
        r.setFolioSerie(getFolioSerie());
        r.setFolioType(getFolioType());
        r.setFolioTypeName(getFolioTypeName());
        r.setId(getId());
        r.setInCost(getInCost());
        r.setInPrice(getInPrice());
        r.setInQuantity(getInQuantity());
        r.setItem(getItem());
        r.setMovement(getMovement());
        r.setOutCost(getOutCost());
        r.setOutPrice(getOutPrice());
        r.setOutQuantity(getOutQuantity());
        r.setType(getType());
        r.setWarehouse(getWarehouse());
        return r;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull KardexDetail toModel() {
        var r = new KardexDetail();
        r.setBalanceCost(getBalanceCost());
        r.setBalancePrice(getBalancePrice());
        r.setBalanceQuantity(getBalanceQuantity());
        r.setBeforeCost(getBeforeCost());
        r.setBeforePrice(getBeforePrice());
        r.setBeforeQuantity(getBeforeQuantity());
        r.setDocDate(getDocDate());
        r.setFolioNumber(getFolioNumber());
        r.setFolioSerie(getFolioSerie());
        r.setFolioType(getFolioType());
        r.setFolioTypeName(getFolioTypeName());
        r.setId(getId());
        r.setInCost(getInCost());
        r.setInPrice(getInPrice());
        r.setInQuantity(getInQuantity());
        r.setItem(getItem());
        r.setMovement(getMovement());
        r.setOutCost(getOutCost());
        r.setOutPrice(getOutPrice());
        r.setOutQuantity(getOutQuantity());
        r.setType(getType());
        r.setWarehouse(getWarehouse());
        return r;
    }

    @Override
    public void fromModel(@NotNull KardexDetail model) {
        setBalanceCost(model.getBalanceCost());
        setBalancePrice(model.getBalancePrice());
        setBalanceQuantity(model.getBalanceQuantity());
        setBeforeCost(model.getBeforeCost());
        setBeforePrice(model.getBeforePrice());
        setBeforeQuantity(model.getBeforeQuantity());
        setDocDate(model.getDocDate());
        setFolioNumber(model.getFolioNumber());
        setFolioSerie(model.getFolioSerie());
        setFolioType(model.getFolioType());
        setFolioTypeName(model.getFolioTypeName());
        setId(model.getId());
        setInCost(model.getInCost());
        setInPrice(model.getInPrice());
        setInQuantity(model.getInQuantity());
        setItem(model.getItem());
        setMovement(model.getMovement());
        setOutCost(model.getOutCost());
        setOutPrice(model.getOutPrice());
        setOutQuantity(model.getOutQuantity());
        setType(model.getType());
        setWarehouse(model.getWarehouse());
    }
}
