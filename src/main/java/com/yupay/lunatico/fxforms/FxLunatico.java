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
     * Holds primary stage instance.
     */
    public static Stage PRIMARY;

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
        PRIMARY = primaryStage;
        MY_APP = this;
        APP_CONTROLLER = FxForms.lunaticoScene();
        APP_CONTROLLER.show(primaryStage);
    }

    @Override
    public void stop() {
        DataSource.closeAll();
    }
}
