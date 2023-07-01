package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxListActiveFlow;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxUnitMV;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.ItemType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

/**
 * The dialog card form controller for Items.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxItemCard extends Dialog<FxItemMV>
        implements CardDialog<FxItemMV> {
    /**
     * Formatter for name.
     */
    private final TextFormatter<String> fmtName
            = UppercaseConverter.formatter(0);
    /**
     * Formatter for notes.
     */
    private final TextFormatter<String> fmtNotes
            = UppercaseConverter.formatter(0);
    /**
     * The currently editing value.
     */
    private final ObjectProperty<FxItemMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * The card edition mode.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    /**
     * Holds false if form mode is VIEWER.
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
    /**
     * Holds true if form mode is VIEWER.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private ComboBox<ItemType> cboType;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private ComboBox<FxUnitMV> cboUnit;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private CheckBox chkActive;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private Label lblBalTotal;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private Label lblBalUnitary;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private Label lblBalUnits;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private Label lblCreatedStamp;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private Label lblID;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private Label lblOwner;
    /**
     * FXML control injected from item-card.fxml
     */
    @FXML
    private DialogPane top;

    public FxItemCard() {
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
    }

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setOnShown(e -> onShown());
        setDialogPane(top);
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);
        setTitle("Editar Artículo");
        valueProperty().addListener(new ValueChanged());
        top.lookupButton(ButtonType.OK).disableProperty().bind(
                Bindings.createBooleanBinding(this::failed,
                        formModeProperty(),
                        fmtName.valueProperty(),
                        cboType.valueProperty(),
                        cboUnit.valueProperty())
        );
    }

    /**
     * What to do when the dialog is shown.
     */
    @FXML
    void onShown() {
        FxListActiveFlow.unit()
                .withBefore(cboUnit.getItems()::clear)
                .withForEach(cboUnit.getItems()::add)
                .go();
    }

    @Override
    public @NotNull FxItemMV blank() {
        var r = new FxItemMV();
        r.setActive(true);
        r.setOwner(FxLunatico.APP_CONTROLLER.getLoggedUser());
        return r;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxItemMV getValue() {
        return value.get();
    }

    @Override
    public void setValue(@NotNull FxItemMV value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxItemMV> valueProperty() {
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
        formMode.set(editorMode);
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
    public @NotNull Dialog<FxItemMV> getRoot() {
        return this;
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
     * Public accessor - getter.
     *
     * @return value of {@link #fmtNotes}
     */
    public TextFormatter<String> getFmtNotes() {
        return fmtNotes;
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
     * Check validation conditions to allow user click OK.
     *
     * @return true if any validation condition is not met.
     */
    private boolean failed() {
        return getFormMode() == EditorMode.VIEWER
                || fmtName.getValue() == null
                || fmtName.getValue().isBlank()
                || cboType.getValue() == null
                || cboUnit.getValue() == null;

    }


    /**
     * Tracker of changes in editing value.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxItemMV> {
        @Override
        public void bind(@NotNull FxItemMV value) {
            lblID.textProperty().bind(value.idProperty().asString());
            fmtName.valueProperty().bindBidirectional(value.nameProperty());
            cboType.valueProperty().bindBidirectional(value.typeProperty());
            cboUnit.valueProperty().bindBidirectional(value.unitProperty());
            //Balance tab
            lblBalUnits.textProperty()
                    .bind(NumberStringBinding.forWarehouse(value.balanceUnitsProperty()));
            lblBalUnitary.textProperty()
                    .bind(NumberStringBinding.forMonetized(value.balanceUnitCostProperty()));
            lblBalTotal.textProperty()
                    .bind(NumberStringBinding.forMonetized(value.balanceCostProperty()));
            //Notes tab
            fmtNotes.valueProperty().bindBidirectional(value.notesProperty());
            //Metadata tab
            chkActive.selectedProperty().bindBidirectional(value.activeProperty());
            lblOwner.textProperty().bind(value.ownerProperty().asString());
            lblCreatedStamp.textProperty()
                    .bind(new TimeStampStringBinding<>(value.createdProperty()));
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblID, fmtName, cboType, cboUnit,
                            lblBalUnits, lblBalUnitary,
                            lblBalTotal, fmtNotes, chkActive, lblOwner, lblCreatedStamp)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxItemMV value) {
            lblID.textProperty().unbind();
            fmtName.valueProperty().unbindBidirectional(value.nameProperty());
            cboType.valueProperty().unbindBidirectional(value.typeProperty());
            cboUnit.valueProperty().unbindBidirectional(value.unitProperty());
            //Balance tab
            lblBalUnits.textProperty().unbind();
            lblBalUnitary.textProperty().unbind();
            lblBalTotal.textProperty().unbind();
            //Notes tab
            fmtNotes.valueProperty().unbindBidirectional(value.notesProperty());
            //Metadata tab
            chkActive.selectedProperty().unbindBidirectional(value.activeProperty());
            lblOwner.textProperty().unbind();
            lblCreatedStamp.textProperty().unbind();
        }
    }
}
