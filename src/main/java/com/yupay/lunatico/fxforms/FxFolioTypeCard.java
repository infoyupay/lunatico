package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxmview.FxFolioTypeMV;
import com.yupay.lunatico.fxtools.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

/**
 * Dialog form card for Folio Type.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxFolioTypeCard
        extends Dialog<FxFolioTypeMV>
        implements CardDialog<FxFolioTypeMV> {
    /**
     * The currently editing value.
     */
    private final ObjectProperty<FxFolioTypeMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * The form card editing mode.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    /**
     * Formatter for name.
     */
    private final TextFormatter<String> fmtName
            = UppercaseConverter.formatter(-1);
    /**
     * Holds true when form mode is viewer.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * Holds true when form mode is not viewer.
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
    /**
     * FXML control injected from folio_type-card.fxml
     */
    @FXML
    private Label lblID;
    /**
     * FXML control injected from folio_type-card.fxml
     */
    @FXML
    private CheckBox chkActive;
    /**
     * FXML control injected from folio_type-card.fxml
     */
    @FXML
    private DialogPane top;

    /**
     * Default constructor. Weaves basic bindings.
     */
    public FxFolioTypeCard() {
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
    }

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setTitle("Editor de Tipos de Folio");
        setDialogPane(top);
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);
        valueProperty().addListener(new ValueChanged());
        top.lookupButton(ButtonType.OK).disableProperty().bind(Bindings.createBooleanBinding(
                this::failed, fmtName.valueProperty(), formModeProperty()));
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxFolioTypeMV getValue() {
        return value.get();
    }

    @Override
    public void setValue(@NotNull FxFolioTypeMV value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxFolioTypeMV> valueProperty() {
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
    public void setFormMode(@NotNull EditorMode formMode) {
        this.formMode.set(formMode);
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
     * FX Accessor - property.
     *
     * @return property {@link #formMode}.
     */
    public final ObjectProperty<EditorMode> formModeProperty() {
        return formMode;
    }

    @Override
    public @NotNull FxFolioTypeMV blank() {
        return FxFolioTypeMV.blank();
    }

    @Override
    public @NotNull Dialog<FxFolioTypeMV> getRoot() {
        return this;
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
     * Computes true if the OK button should be
     * disabled. This is true when form editor
     * mode is viewer, or name value is null,
     * or name value is blank.
     *
     * @return computation boolean value.
     */
    private boolean failed() {
        return isViewer()
                || fmtName.getValue() == null
                || fmtName.getValue().isBlank();
    }

    /**
     * Value listener to keep track of bindings
     * when editing value changes.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxFolioTypeMV> {
        @Override
        public void bind(@NotNull FxFolioTypeMV value) {
            lblID.textProperty().bind(value.idProperty().asString("%d"));
            fmtName.valueProperty().bindBidirectional(value.nameProperty());
            chkActive.selectedProperty().bindBidirectional(value.activeProperty());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblID, fmtName, chkActive)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxFolioTypeMV value) {
            lblID.textProperty().unbind();
            fmtName.valueProperty().unbindBidirectional(value.nameProperty());
            chkActive.selectedProperty().unbindBidirectional(value.activeProperty());
        }
    }
}
