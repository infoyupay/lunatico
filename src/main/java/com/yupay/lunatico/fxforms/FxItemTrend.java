package com.yupay.lunatico.fxforms;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

public class FxItemTrend extends Alert {
    @FXML
    private DialogPane top;

    public FxItemTrend() {
        super(AlertType.NONE);
    }

    @FXML
    void initialize() {
        setDialogPane(top);
        setTitle("Tendencia de Logística");
    }
}
