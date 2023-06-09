package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxListActiveFlow;
import com.yupay.lunatico.fxmview.*;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.MovementType;
import com.yupay.lunatico.model.Person;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

import static javafx.collections.FXCollections.observableArrayList;

/**
 * The movement card dialog.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxMovementCard extends Dialog<FxMovementMV>
        implements CardDialog<FxMovementMV> {

    /**
     * Currently editing value.
     */
    private final ObjectProperty<FxMovementMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * Holds the data for details.
     */
    private final ListProperty<FxMovementLineMV> detailData =
            new SimpleListProperty<>(this, "detailData", observableArrayList());
    /**
     * The form modality.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private ComboBox<FxFolioTypeMV> cboFolioType;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private ComboBox<FxWarehouseMV> cboStore;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private ComboBox<MovementType> cboType;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLineMV, Integer> colDtId;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLineMV, FxItemMV> colDtItemCode;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLineMV, FxItemMV> colDtItemName;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLineMV, BigDecimal> colDtQty;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLineMV, FxItemMV> colDtUnit;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private DatePicker dtpOpDate;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private Label lblID;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private Label lblRecDate;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private Label lblUser;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private SearchableTextField<Person, FxPersonMV> txtPerson;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableView<FxMovementLineMV> tblDetail;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private DialogPane top;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TextFormatter<String> fmtFolioSerie;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TextFormatter<String> fmtFolioNumber;
    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TextFormatter<String> fmtNotes;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setDialogPane(top);
        setTitle("Kárdex de Movimiento");
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);
        setOnShown(e -> onShown());

        tblDetail.setItems(detailData);
        tblDetail.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        new ValueFactoryManager<FxMovementLineMV>()
                .addInt(colDtId, FxMovementLineMV::lineProperty)
                .add(colDtItemCode, FxMovementLineMV::itemProperty)
                .add(colDtItemName, FxMovementLineMV::itemProperty)
                .add(colDtQty, FxMovementLineMV::quantityProperty)
                .add(colDtUnit, FxMovementLineMV::itemProperty)
                .provide();

        cboStore.setConverter(FromListStringConverter.forWarehouse(cboStore));
        cboFolioType.setConverter(FromListStringConverter.forFolioType(cboFolioType));
    }

    /**
     * Event handler to be invoked when showing this dialog.
     */
    private void onShown() {
        FxListActiveFlow.warehouse()
                .withBefore(cboStore.getItems()::clear)
                .withForEach(cboStore.getItems()::add)
                .go();

        FxListActiveFlow.folioType()
                .withBefore(cboFolioType.getItems()::clear)
                .withForEach(cboFolioType.getItems()::add)
                .go();

    }

    /**
     * FXML event handler.
     */
    @FXML
    void onCleanAction() {
        detailData.clear();
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onCreateAction() {
        FxForms.movementLineCard()
                .creator(null)
                .filter(x -> detailData
                        .stream()
                        .anyMatch(p -> Objects.equals(p.getItem(), x.getItem())))
                .ifPresent(x -> {
                    detailData.add(x);
                    flushLineNumber();
                });
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onDeleteAction() {
        var sel = tblDetail.getSelectionModel().getSelectedItems();
        for (var x : sel) {
            detailData.removeIf(p -> p.getLine() == x.getLine());
        }
        flushLineNumber();
    }

    /**
     * FXML event handler.
     *
     * @param event the event object.
     */
    @FXML
    void onTableClicked(@NotNull MouseEvent event) {
        if (!event.isConsumed()
                && event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() > 1) {
            var opt = Optional.ofNullable(tblDetail.getSelectionModel().getSelectedItem())
                    .map(FxMovementLineMV::deepCopy);
            if (getFormMode() != EditorMode.CREATOR) {
                opt.ifPresent(x -> FxForms.movementLineCard().viewer(null, x));
            } else {
                opt.flatMap(x -> FxForms.movementLineCard().editor(null, x))
                        .ifPresent(Functionals.replace(detailData));
            }
            event.consume();
        }
    }

    /**
     * Sets tie line number on each element.
     */
    private void flushLineNumber() {
        IntStream.range(0, detailData.size())
                .forEach(i -> detailData.get(i).setLine((short) (i + 1)));
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxMovementMV getValue() {
        return value.get();
    }

    @Override
    public final void setValue(@NotNull FxMovementMV value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxMovementMV> valueProperty() {
        return value;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #detailData}.get();
     */
    public final ObservableList<FxMovementLineMV> getDetailData() {
        return detailData.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param detailData value to assign into {@link #detailData}.
     */
    public final void setDetailData(ObservableList<FxMovementLineMV> detailData) {
        this.detailData.set(detailData);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #detailData}.
     */
    public final ListProperty<FxMovementLineMV> detailDataProperty() {
        return detailData;
    }

    @Override
    public @NotNull FxMovementMV blank() {
        var r = new FxMovementMV();
        r.setOwner(FxLunatico.APP_CONTROLLER.getLoggedUser());
        return r;
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
    public @NotNull Dialog<FxMovementMV> getRoot() {
        return this;
    }

    private class ValueChanged implements CardValueListener<FxMovementMV> {
        @Override
        public void bind(@NotNull FxMovementMV value) {
            detailData.bindBidirectional(value.detailProperty());
            cboFolioType.valueProperty().bindBidirectional(value.folioTypeProperty());
            cboStore.valueProperty().bindBidirectional(value.warehouseProperty());
            cboType.valueProperty().bindBidirectional(value.typeProperty());
            lblID.textProperty().bind(value.idProperty().asString("%d"));
            lblRecDate.textProperty().bind(new TimeStampStringBinding<>(value.ownDateProperty()));
            lblUser.textProperty().bind(value.ownerProperty().asString());
            txtPerson.valueProperty().bindBidirectional(value.personProperty());
            fmtFolioNumber.valueProperty().bindBidirectional(value.folioNumberProperty());
            fmtFolioSerie.valueProperty().bindBidirectional(value.folioSerieProperty());
            fmtNotes.valueProperty().bindBidirectional(value.notesProperty());
            dtpOpDate.valueProperty().bindBidirectional(value.docDateProperty());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(detailData,
                            cboFolioType, cboStore, cboType,
                            lblID, lblRecDate, lblUser,
                            txtPerson,
                            fmtFolioNumber, fmtFolioSerie, fmtNotes,
                            dtpOpDate)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxMovementMV value) {
            detailData.unbindBidirectional(value.detailProperty());
            cboFolioType.valueProperty().unbindBidirectional(value.folioTypeProperty());
            cboStore.valueProperty().unbindBidirectional(value.warehouseProperty());
            cboType.valueProperty().unbindBidirectional(value.typeProperty());
            lblID.textProperty().unbind();
            lblRecDate.textProperty().unbind();
            lblUser.textProperty().unbind();
            txtPerson.valueProperty().unbindBidirectional(value.personProperty());
            fmtFolioNumber.valueProperty().unbindBidirectional(value.folioNumberProperty());
            fmtFolioSerie.valueProperty().unbindBidirectional(value.folioSerieProperty());
            fmtNotes.valueProperty().unbindBidirectional(value.notesProperty());
            dtpOpDate.valueProperty().unbindBidirectional(value.docDateProperty());
        }
    }
}
