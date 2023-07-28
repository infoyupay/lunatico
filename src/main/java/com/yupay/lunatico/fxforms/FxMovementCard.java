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

import com.yupay.lunatico.fxflows.FxListActiveFlow;
import com.yupay.lunatico.fxmview.*;
import com.yupay.lunatico.fxtools.*;
import com.yupay.lunatico.model.MovementType;
import com.yupay.lunatico.model.Person;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
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
     * Holds true when form editor mode is equal to VIEWER.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * Holds true when form editor mode is equals to VIEWER.
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
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
    private TableColumn<FxMovementLineMV, BigDecimal> colDtPriceRef;
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
     * Default constructor, shall initialize
     * form editor mode bindings.
     */
    public FxMovementCard() {
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
    }

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setDialogPane(top);
        setTitle("Kárdex de Movimiento");
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);

        tblDetail.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        new ValueFactoryManager<FxMovementLineMV>()
                .addInt(colDtId, FxMovementLineMV::lineProperty)
                .add(colDtItemCode, FxMovementLineMV::itemProperty)
                .add(colDtItemName, FxMovementLineMV::itemProperty)
                .add(colDtQty, FxMovementLineMV::quantityProperty)
                .add(colDtPriceRef, FxMovementLineMV::priceRefProperty)
                .add(colDtUnit, FxMovementLineMV::itemProperty)
                .provide();

        cboStore.setConverter(FromListStringConverter.forWarehouse(cboStore));
        cboFolioType.setConverter(FromListStringConverter.forFolioType(cboFolioType));
        loadCombos();

        valueProperty().addListener(new ValueChanged());
        top.lookupButton(ButtonType.OK).disableProperty().bind(viewer);
    }

    /**
     * Event handler to be invoked when showing this dialog.
     */
    private void loadCombos() {
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
                        .noneMatch(p -> Objects.equals(p.getItem(), x.getItem())))
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
                opt.flatMap(x -> FxForms.movementLineCard().editor(null, x.deepCopy()))
                        .ifPresent(dt -> {
                            for (int i = 0, sz = detailData.getSize();
                                 i < sz; i++) {
                                var x = detailData.get(i);
                                if (x.getLine() == dt.getLine()) {
                                    detailData.set(i, dt);
                                }
                            }
                        });
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
    @SuppressWarnings("unused")
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

    /**
     * Bindings keeper for the card value.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxMovementMV> {
        @Override
        public void bind(@NotNull FxMovementMV value) {
            detailData.setValue(value.getDetail());
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
            detailData.setValue(FXCollections.emptyObservableList());
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
