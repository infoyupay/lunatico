package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxmview.*;
import com.yupay.lunatico.model.MovementType;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

/**
 * The movement card dialog.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class MovementCard extends Dialog<FxMovement> {

    /**
     * Currently editing value.
     */
    private final ObjectProperty<FxMovement> value =
            new SimpleObjectProperty<>(this, "value");


    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private ComboBox<FxFolioTypeCard> cboFolioType;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private ComboBox<FxStore> cboStore;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private ComboBox<MovementType> cboType;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLine, Integer> colDtId;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLine, FxItem> colDtItemCode;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLine, FxItem> colDtItemName;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLine, BigDecimal> colDtQty;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLine, String> colDtRemark;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private TableColumn<FxMovementLine, FxUnit> colDtUnit;

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
    private TableView<FxMovementLine> tblDetail;

    /**
     * FXML control injected from movement-card.fxml
     */
    @FXML
    private DialogPane top;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setDialogPane(top);
        setTitle("Kárdex de Movimiento");
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onCleanAction() {

    }

    /**
     * FXML event handler.
     */
    @FXML
    void onCreateAction() {

    }

    /**
     * FXML event handler.
     */
    @FXML
    void onDeleteAction() {

    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxMovement getValue() {
        return value.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param value value to assign into {@link #value}.
     */
    public final void setValue(FxMovement value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxMovement> valueProperty() {
        return value;
    }

}
