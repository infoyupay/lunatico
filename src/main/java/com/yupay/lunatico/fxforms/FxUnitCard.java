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

import com.yupay.lunatico.fxmview.FxUnitMV;
import com.yupay.lunatico.fxtools.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

/**
 * Dialog card for measurement Units entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUnitCard extends Dialog<FxUnitMV>
        implements CardDialog<FxUnitMV> {

    /**
     * The currently editing value.
     */
    private final ObjectProperty<FxUnitMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * The form editor card mode.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    /**
     * Text formatter for tag.
     */
    private final TextFormatter<String> fmtTag =
            UppercaseConverter.formatter(0);
    /**
     * Text formatter for symbol.
     */
    private final TextFormatter<String> fmtSymbol =
            UppercaseConverter.formatter(0);
    /**
     * Holds true if form mode is NOT viewer.
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
    /**
     * Holds true if mode is viewer.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * FXML control injected from unit-card.fxml
     */
    @FXML
    private CheckBox chkActive;
    /**
     * FXML control injected from unit-card.fxml
     */
    @FXML
    private Label lblID;
    /**
     * FXML control injected from unit-card.fxml
     */
    @FXML
    private DialogPane top;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setTitle("Editar Unidad de Medida");
        setDialogPane(top);
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);
        valueProperty().addListener(new ValueChanged());
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
        top.lookupButton(ButtonType.OK).disableProperty().bind(viewer);
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxUnitMV getValue() {
        return value.get();
    }

    /**
     * Sets the editing value of the form.
     *
     * @param value the new editing value.
     */
    @Override
    public void setValue(@NotNull FxUnitMV value) {
        this.value.setValue(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxUnitMV> valueProperty() {
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

    /**
     * Sets the form editor mode.
     *
     * @param editorMode the editor mode.
     */
    @Override
    public void setFormMode(@NotNull EditorMode editorMode) {
        this.formMode.setValue(editorMode);
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
     * Public accessor - getter.
     *
     * @return value of {@link #fmtTag}
     */
    public TextFormatter<String> getFmtTag() {
        return fmtTag;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #fmtSymbol}
     */
    public TextFormatter<String> getFmtSymbol() {
        return fmtSymbol;
    }

    /**
     * Creates a new item (in blank) with default values.
     *
     * @return a new item.
     */
    @Override
    public @NotNull FxUnitMV blank() {
        var r = new FxUnitMV();
        r.setActive(true);
        return r;
    }

    /**
     * Gets the root element as a Dialog object.
     *
     * @return the dialog object (this).
     */
    @Override
    public @NotNull Dialog<FxUnitMV> getRoot() {
        return this;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #notViewer}.get();
     */
    public final boolean isNotViewer() {
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

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #viewer}.get();
     */
    public final boolean getViewer() {
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
     * Listener to keep track of bindings.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxUnitMV> {
        @Override
        public void bind(@NotNull FxUnitMV v) {
            lblID.textProperty().bind(v.idProperty().asString("%06d"));
            fmtSymbol.valueProperty().bindBidirectional(v.symbolProperty());
            fmtTag.valueProperty().bindBidirectional(v.tagProperty());
            chkActive.selectedProperty().bindBidirectional(v.activeProperty());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblID, fmtSymbol, fmtTag, chkActive)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxUnitMV v) {
            lblID.textProperty().unbind();
            fmtSymbol.valueProperty().unbindBidirectional(v.symbolProperty());
            fmtTag.valueProperty().unbindBidirectional(v.tagProperty());
            chkActive.selectedProperty().unbindBidirectional(v.activeProperty());
        }
    }
}
