/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxMovementLineMV;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.Item;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

/**
 * JavaFX controller for the Movement Detail Line card.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
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
    /**
     * Read-only flag that holds true if editor mode is equals to VIEWER.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * Read-only flag that holds true if editor mode is different from VIEWER.
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
    /**
     * FXML control injected from movement_line-card.fxml
     */
    @FXML
    private Label lblLine;
    /**
     * FXML control injected from movement_line-card.fxml
     */
    @FXML
    private DialogPane top;
    /**
     * FXML control injected from movement_line-card.fxml
     */
    @FXML
    private SearchableTextField<Item, FxItemMV> txtItem;
    /**
     * FXML control injected from movement_line-card.fxml
     */
    @FXML
    private TextFormatter<BigDecimal> fmtQuantity;
    /**
     * FXML control injected from movement_line-card.fxml
     */
    @FXML
    private TextFormatter<BigDecimal> fmtPriceRef;

    /**
     * Default constructor that initializes some bindigns.
     */
    public FxMovementLnCard() {
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
    }

    /**
     * FXML initializer.
     */
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

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #viewer}.get();
     */
    public final boolean isViewer() {
        return viewer.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #viewer}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty viewerProperty() {
        return viewer.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #notViewer}.get();
     */
    public final boolean getNotViewer() {
        return notViewer.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #notViewer}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty notViewerProperty() {
        return notViewer.getReadOnlyProperty();
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
            fmtPriceRef.valueProperty().bindBidirectional(value.priceRefProperty());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblLine, txtItem, fmtQuantity, fmtPriceRef)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxMovementLineMV value) {
            lblLine.textProperty().unbind();
            txtItem.valueProperty().unbindBidirectional(value.itemProperty());
            fmtQuantity.valueProperty().unbindBidirectional(value.quantityProperty());
            fmtPriceRef.valueProperty().unbindBidirectional(value.priceRefProperty());
        }
    }
}
