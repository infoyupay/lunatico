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

import com.yupay.lunatico.fxmview.FxPersonMV;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.DoiType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

/**
 * JavaFX controller for Person card form.
 * See GUI definition at person-card.fxml
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxPersonCard
        extends Dialog<FxPersonMV>
        implements CardDialog<FxPersonMV> {

    /**
     * Currently editing value.
     */
    private final ObjectProperty<FxPersonMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * Form editing mode.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    /**
     * Formatter for DOI number.
     */
    private final TextFormatter<String> fmtDoiNum
            = UppercaseConverter.formatter(20);
    /**
     * Formatter for person's name.
     */
    private final TextFormatter<String> fmtName
            = UppercaseConverter.formatter(-1);
    /**
     * Holds true whenever the form card mode is viewer.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * Holds true whenever the form card mode is NOT viewer.
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
    /**
     * FXML control injected from person-card.fxml
     */
    @FXML
    private ComboBox<DoiType> cboDoiType;
    /**
     * FXML control injected from person-card.fxml
     */
    @FXML
    private CheckBox chkActive;
    /**
     * FXML control injected from person-card.fxml
     */
    @FXML
    private Label lblID;
    /**
     * FXML control injected from person-card.fxml
     */
    @FXML
    private DialogPane top;

    /**
     * Blank constructor that wires some properties.
     */
    public FxPersonCard() {
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
    }

    /**
     * FXML initialier.
     */
    @FXML
    void initialize() {
        setDialogPane(top);
        setTitle("Editor de Personas");
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);

        valueProperty().addListener(new ValueChanged());
        top.lookupButton(ButtonType.OK).disableProperty().bind(
                Bindings.createBooleanBinding(this::failed,
                        formModeProperty(),
                        fmtDoiNum.valueProperty(),
                        fmtName.valueProperty(),
                        cboDoiType.valueProperty())
        );
    }

    @Override
    public @NotNull FxPersonMV blank() {
        return FxPersonMV.blank();
    }

    @Override
    public @NotNull Dialog<FxPersonMV> getRoot() {
        return this;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxPersonMV getValue() {
        return value.get();
    }

    @Override
    public void setValue(@NotNull FxPersonMV value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxPersonMV> valueProperty() {
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
     * Public accessor - getter.
     *
     * @return value of {@link #fmtDoiNum}
     */
    public TextFormatter<String> getFmtDoiNum() {
        return fmtDoiNum;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #fmtName}
     */
    public TextFormatter<String> getFmtName() {
        return fmtName;
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
     * Computes if user input is valid.
     *
     * @return false whenever user input is valid.
     * True if failed assertions.
     */
    private boolean failed() {
        return isViewer()
                || fmtName.getValue() == null
                || fmtName.getValue().isBlank()
                || assertDoiNumFail(cboDoiType.getValue(), fmtDoiNum.getValue());
    }

    /**
     * Asserts that doiNumber and doiType fails check.
     *
     * @param doiType the doi type to check.
     * @param doiNum  the doi number to check.
     * @return true if assertion fails.
     */
    private boolean assertDoiNumFail(DoiType doiType,
                                     String doiNum) {
        var doiNumPresent = !(doiNum == null || doiNum.isBlank());
        var doiTypePresent = doiType != null;
        return doiTypePresent != doiNumPresent;
    }

    /**
     * Listener to keep track of bindings when value changes.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxPersonMV> {
        @Override
        public void bind(@NotNull FxPersonMV value) {
            lblID.textProperty().bind(value.idProperty().asString("%d"));
            fmtName.valueProperty().bindBidirectional(value.nameProperty());
            fmtDoiNum.valueProperty().bindBidirectional(value.doiNumProperty());
            cboDoiType.valueProperty().bindBidirectional(value.doiTypeProperty());
            chkActive.selectedProperty().bindBidirectional(value.activeProperty());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblID, fmtName, fmtDoiNum, cboDoiType, chkActive)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxPersonMV value) {
            lblID.textProperty().unbind();
            fmtName.valueProperty().unbindBidirectional(value.nameProperty());
            fmtDoiNum.valueProperty().unbindBidirectional(value.doiNumProperty());
            cboDoiType.valueProperty().unbindBidirectional(value.doiTypeProperty());
            chkActive.selectedProperty().unbindBidirectional(value.activeProperty());
        }
    }
}
