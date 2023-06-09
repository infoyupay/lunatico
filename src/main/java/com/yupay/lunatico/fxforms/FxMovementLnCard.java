package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxMovementLineMV;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class FxMovementLnCard extends Dialog<FxMovementLineMV>
        implements CardDialog<FxMovementLineMV> {

    /**
     * Currently editing value.
     */
    private final ObjectProperty<FxMovementLineMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * Editor card mode.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    @FXML
    private Label lblLine;
    @FXML
    private DialogPane top;
    @FXML
    private SearchableTextField<Item, FxItemMV> txtItem;
    @FXML
    private TextFormatter<BigDecimal> fmtQuantity;

    @FXML
    void initialize() {
        setDialogPane(top);
        setTitle("Editar Detalle");
        setResultConverter(b -> ButtonType.OK == b ? getValue() : null);
        valueProperty().addListener(new ValueChanged());
    }

    @Override
    public @NotNull FxMovementLineMV blank() {
        return new FxMovementLineMV();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxMovementLineMV getValue() {
        return value.get();
    }

    @Override
    public void setValue(@NotNull FxMovementLineMV value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxMovementLineMV> valueProperty() {
        return value;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #formMode}.get();
     */
    public final EditorMode getFormMode() {
        return formMode.get();
    }

    @Override
    public void setFormMode(@NotNull EditorMode editorMode) {
        this.formMode.set(editorMode);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #formMode}.
     */
    public final ObjectProperty<EditorMode> formModeProperty() {
        return formMode;
    }

    @Override
    public @NotNull Dialog<FxMovementLineMV> getRoot() {
        return this;
    }

    /**
     * Binding keeper for when value changes.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxMovementLineMV> {
        @Override
        public void bind(@NotNull FxMovementLineMV value) {
            lblLine.textProperty().bind(value.lineProperty().asString());
            txtItem.valueProperty().bindBidirectional(value.itemProperty());
            fmtQuantity.valueProperty().bindBidirectional(value.quantityProperty());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblLine, txtItem, fmtQuantity)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxMovementLineMV value) {
            lblLine.textProperty().unbind();
            txtItem.valueProperty().unbindBidirectional(value.itemProperty());
            fmtQuantity.valueProperty().unbindBidirectional(value.quantityProperty());
        }
    }
}
