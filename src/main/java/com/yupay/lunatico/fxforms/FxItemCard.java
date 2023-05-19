package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.model.ItemType;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FxItemCard extends Dialog<Object> {

    @FXML
    private ComboBox<ItemType> cboType;

    @FXML
    private ComboBox<?> cboUnit;

    @FXML
    private CheckBox chkActive;

    @FXML
    private Label lblBalOnsale;

    @FXML
    private Label lblBalStorage;

    @FXML
    private Label lblBalTotal;

    @FXML
    private Label lblBalUnitary;

    @FXML
    private Label lblBalUnits;

    @FXML
    private Label lblCreatedStamp;

    @FXML
    private Label lblID;

    @FXML
    private Label lblOwner;

    @FXML
    private DialogPane top;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtNotes;

}
