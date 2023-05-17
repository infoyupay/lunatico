package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxmview.FxWarehouseMV;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.VirtualWarehouseType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

/**
 * A card dialog to allow user to make inputs on a warehouse entity.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxWarehouseCard extends Dialog<FxWarehouseMV>
        implements CardDialog<FxWarehouseMV> {

    /**
     * The currently editing value.
     */
    private final ObjectProperty<FxWarehouseMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * The editor mode of this card dialog.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    /**
     * Formatter for name.
     */
    private final TextFormatter<String> fmtName =
            UppercaseConverter.formatter(0);
    /**
     * Holds true if the formMode is not CREATOR.
     */
    private final ReadOnlyBooleanWrapper notCreator =
            new ReadOnlyBooleanWrapper(this, "notCreator");
    /**
     * Holds true if the formMode is VIEWER.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * Holds true if the formMode is not VIEWER
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
    /**
     * FXML control injected from warehouse-card.fxml
     */
    @FXML
    private ComboBox<VirtualWarehouseType> cboType;
    /**
     * FXML control injected from warehouse-card.fxml
     */
    @FXML
    private CheckBox chkActive;
    /**
     * FXML control injected from warehouse-card.fxml
     */
    @FXML
    private Label lblID;
    /**
     * FXML control injected from warehouse-card.fxml
     */
    @FXML
    private TextField txtName;
    /**
     * FXML control injected from warehouse-card.fxml
     */
    @FXML
    private DialogPane top;

    /**
     * Default constructor that initializes basic bindings.
     */
    public FxWarehouseCard() {
        notCreator.bind(formModeProperty().isNotEqualTo(EditorMode.CREATOR));
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
    }

    /**
     * FXLM initializer.
     */
    @FXML
    void initialize() {
        setDialogPane(top);
        setTitle("Editor de Almacén");
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);
        valueProperty().addListener(new ValueChanged());
        top.lookupButton(ButtonType.OK)
                .disableProperty()
                .bind(Bindings.createBooleanBinding(
                        this::acceptLocked,
                        txtName.textProperty(),
                        formModeProperty()
                ));
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onClearType() {
        cboType.setValue(null);
    }

    /**
     * Creates a new item (in blank) with default values.
     *
     * @return a new item.
     */
    @Override
    public @NotNull FxWarehouseMV blank() {
        var r = new FxWarehouseMV();
        r.setActive(true);
        return r;
    }

    /**
     * Gets the root element as a Dialog object.
     *
     * @return the dialog object (this).
     */
    @Override
    public @NotNull Dialog<FxWarehouseMV> getRoot() {
        return this;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxWarehouseMV getValue() {
        return value.get();
    }

    @Override
    public void setValue(@NotNull FxWarehouseMV value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxWarehouseMV> valueProperty() {
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
     * @return value of {@link #fmtName}
     */
    public TextFormatter<String> getFmtName() {
        return fmtName;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #notCreator}.get();
     */
    public final boolean isNotCreator() {
        return notCreator.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #notCreator}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty notCreatorProperty() {
        return notCreator.getReadOnlyProperty();
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
     * Computes if accept (OK) button should be locked.
     * This is meant to be used in a boolean binding.
     *
     * @return true if OK should be locked (disabled).
     */
    private boolean acceptLocked() {
        if (isViewer()) return true;
        var str = txtName.getText();
        return str == null || str.isBlank();
    }

    /**
     * Inner listener to keep track of GUI bindings and entity model view.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxWarehouseMV> {
        @Override
        public void bind(@NotNull FxWarehouseMV value) {
            lblID.textProperty().bind(value.idProperty().asString("%04d"));
            fmtName.valueProperty().bindBidirectional(value.nameProperty());
            cboType.valueProperty().bindBidirectional(value.virtualTypeProperty());
            chkActive.selectedProperty().bindBidirectional(value.activeProperty());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblID, fmtName, cboType, chkActive)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxWarehouseMV value) {
            lblID.textProperty().unbind();
            fmtName.valueProperty().unbindBidirectional(value.nameProperty());
            cboType.valueProperty().unbindBidirectional(value.virtualTypeProperty());
            chkActive.selectedProperty().unbindBidirectional(value.activeProperty());
        }
    }
}
