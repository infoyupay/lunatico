package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.dao.DataSource;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * The main class, application entry point.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxLunatico extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        FxForms.lunaticoScene().show(primaryStage);
    }

    @Override
    public void stop() {
        DataSource.closeAll();
    }
}
