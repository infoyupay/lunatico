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
    /**
     * Holds application instance.
     */
    public static FxLunatico MY_APP;
    /**
     * Holds application main window controller instance.
     */
    public static LunaticoScene APP_CONTROLLER;

    /**
     * Entry point to the application.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(@NotNull Stage primaryStage) {
        MY_APP = this;
        APP_CONTROLLER = FxForms.lunaticoScene();
        APP_CONTROLLER.show(primaryStage);
    }

    @Override
    public void stop() {
        DataSource.closeAll();
    }
}
