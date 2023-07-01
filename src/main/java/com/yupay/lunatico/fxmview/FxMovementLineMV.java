package com.yupay.lunatico.fxmview;

import com.yupay.lunatico.model.ModelView;
import com.yupay.lunatico.model.MovementDetail;
import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Model view implementation for movement detail line.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxMovementLineMV extends ModelView<MovementDetail, FxMovementLineMV> {
    /**
     * The autogenerated ID.
     */
    private final LongProperty id =
            new SimpleLongProperty(this, "id");
    /**
     * Line order number.
     */
    private final IntegerProperty line =
            new SimpleIntegerProperty(this, "line");
    /**
     * Movement.
     */
    private final ObjectProperty<FxMovementMV> movement =
            new SimpleObjectProperty<>(this, "movement");
    /**
     * Moved Item.
     */
    private final ObjectProperty<FxItemMV> item =
            new SimpleObjectProperty<>(this, "item");
    /**
     * Quantity before movement.
     */
    private final ObjectProperty<BigDecimal> beforeQuantity =
            new SimpleObjectProperty<>(this, "beforeQuantity", new BigDecimal("0.00000000"));
    /**
     * Price before movement.
     */
    private final ObjectProperty<BigDecimal> beforePrice =
            new SimpleObjectProperty<>(this, "beforePrice", new BigDecimal("0.00000000"));
    /**
     * Cost before movement.
     */
    private final ObjectProperty<BigDecimal> beforeCost =
            new SimpleObjectProperty<>(this, "beforeCost", new BigDecimal("0.00000000"));
    /**
     * Quantity input.
     */
    private final ObjectProperty<BigDecimal> inQuantity =
            new SimpleObjectProperty<>(this, "inQuantity", new BigDecimal("0.00000000"));
    /**
     * Price (unary) input.
     */
    private final ObjectProperty<BigDecimal> inPrice =
            new SimpleObjectProperty<>(this, "inPrice", new BigDecimal("0.00000000"));
    /**
     * Cost input.
     */
    private final ObjectProperty<BigDecimal> inCost =
            new SimpleObjectProperty<>(this, "inCost", new BigDecimal("0.00000000"));
    /**
     * Quantity out.
     */
    private final ObjectProperty<BigDecimal> outQuantity =
            new SimpleObjectProperty<>(this, "outQuantity", new BigDecimal("0.00000000"));
    /**
     * Price (unary) out.
     */
    private final ObjectProperty<BigDecimal> outPrice =
            new SimpleObjectProperty<>(this, "outPrice", new BigDecimal("0.00000000"));
    /**
     * Cost out.
     */
    private final ObjectProperty<BigDecimal> outCost =
            new SimpleObjectProperty<>(this, "outCost", new BigDecimal("0.00000000"));
    /**
     * Balance quantity.
     */
    private final ObjectProperty<BigDecimal> balanceQuantity =
            new SimpleObjectProperty<>(this, "balanceQuantity", new BigDecimal("0.00000000"));
    /**
     * Balance unary price.
     */
    private final ObjectProperty<BigDecimal> balancePrice =
            new SimpleObjectProperty<>(this, "balancePrice", new BigDecimal("0.00000000"));
    /**
     * Balance total cost.
     */
    private final ObjectProperty<BigDecimal> balanceCost =
            new SimpleObjectProperty<>(this, "balanceCost", new BigDecimal("0.00000000"));
    /**
     * The quantity of the movement (absolute value).
     */
    private final ObjectProperty<BigDecimal> quantity =
            new SimpleObjectProperty<>(this, "quantity", new BigDecimal("0.00000000"));
    /**
     * Referential unit price when set by user.
     */
    private final ObjectProperty<BigDecimal> priceRef =
            new SimpleObjectProperty<>(this, "priceRef", null);

    /**
     * Constructor to copy information from a model entity.
     *
     * @param model the model entity.
     */
    public FxMovementLineMV(@NotNull MovementDetail model) {
        fromModel(model);
    }

    /**
     * Default no-op constructor.
     */
    public FxMovementLineMV() {
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
     * @return value of {@link #line}.get();
     */
    public final short getLine() {
        return line.getValue() == null ? 0 : line.getValue().shortValue();
    }

    /**
     * FX Accessor - setter.
     *
     * @param line value to assign into {@link #line}.
     */
    public final void setLine(short line) {
        this.line.set(line);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #line}.
     */
    public final IntegerProperty lineProperty() {
        return line;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #movement}.get();
     */
    public final FxMovementMV getMovement() {
        return movement.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param movement value to assign into {@link #movement}.
     */
    public final void setMovement(FxMovementMV movement) {
        this.movement.set(movement);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #movement}.
     */
    public final ObjectProperty<FxMovementMV> movementProperty() {
        return movement;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #item}.get();
     */
    public final FxItemMV getItem() {
        return item.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param item value to assign into {@link #item}.
     */
    public final void setItem(FxItemMV item) {
        this.item.set(item);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #item}.
     */
    public final ObjectProperty<FxItemMV> itemProperty() {
        return item;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #quantity}.get();
     */
    public final BigDecimal getQuantity() {
        return quantity.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param quantity value to assign into {@link #quantity}.
     */
    public final void setQuantity(BigDecimal quantity) {
        this.quantity.set(quantity);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #quantity}.
     */
    public final ObjectProperty<BigDecimal> quantityProperty() {
        return quantity;
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
     * @return value of {@link #priceRef}.get();
     */
    public final BigDecimal getPriceRef() {
        return priceRef.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param priceRef value to assign into {@link #priceRef}.
     */
    public final void setPriceRef(BigDecimal priceRef) {
        this.priceRef.set(priceRef);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #priceRef}.
     */
    public final ObjectProperty<BigDecimal> priceRefProperty() {
        return priceRef;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof FxMovementLineMV that &&
                getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull FxMovementLineMV deepCopy() {
        var r = new FxMovementLineMV();
        r.setBalanceCost(getBalanceCost());
        r.setBalancePrice(getBalancePrice());
        r.setBalanceQuantity(getBalanceQuantity());
        r.setBeforeCost(getBeforeCost());
        r.setBeforePrice(getBeforePrice());
        r.setBeforeQuantity(getBeforeQuantity());
        r.setId(getId());
        r.setInCost(getInCost());
        r.setInPrice(getInPrice());
        r.setInQuantity(getInQuantity());
        r.setItem(getItem());
        r.setLine(getLine());
        r.setMovement(getMovement());
        r.setOutCost(getOutCost());
        r.setOutPrice(getOutPrice());
        r.setOutQuantity(getOutQuantity());
        r.setPriceRef(getPriceRef());
        r.setQuantity(getQuantity());
        return r;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public @NotNull MovementDetail toModel() {
        var m = new MovementDetail();
        m.setBalanceCost(getBalanceCost());
        m.setBalancePrice(getBalancePrice());
        m.setBalanceQuantity(getBalanceQuantity());
        m.setBeforeCost(getBeforeCost());
        m.setBeforePrice(getBeforePrice());
        m.setBeforeQuantity(getBeforeQuantity());
        m.setId(getId());
        m.setInCost(getInCost());
        m.setInPrice(getInPrice());
        m.setInQuantity(getInQuantity());
        if (getItem() != null) {
            m.setItem(getItem().toModel());
        }
        m.setLine(getLine());
        if (getMovement() != null) {
            m.setMovement(getMovement().toModel());
        }
        m.setOutCost(getOutCost());
        m.setOutPrice(getOutPrice());
        m.setOutQuantity(getOutQuantity());
        m.setPriceRef(getPriceRef());
        m.setQuantity(getQuantity());
        return m;
    }

    @Override
    public void fromModel(@NotNull MovementDetail m) {
        setBalanceCost(m.getBalanceCost());
        setBalancePrice(m.getBalancePrice());
        setBalanceQuantity(m.getBalanceQuantity());
        setBeforeCost(m.getBeforeCost());
        setBeforePrice(m.getBeforePrice());
        setBeforeQuantity(m.getBeforeQuantity());
        setId(m.getId());
        setInCost(m.getInCost());
        setInPrice(m.getInPrice());
        setInQuantity(m.getInQuantity());
        if (m.getItem() != null) {
            setItem(new FxItemMV(m.getItem()));
        }
        setLine(m.getLine());
        if (m.getMovement() != null) {
            setMovement(new FxMovementMV(m.getMovement()));
        }
        setOutCost(m.getOutCost());
        setOutPrice(m.getOutPrice());
        setOutQuantity(m.getOutQuantity());
        setPriceRef(m.getPriceRef());
        setQuantity(m.getQuantity());
    }
}
