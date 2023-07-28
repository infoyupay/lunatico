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

import com.yupay.lunatico.fxtools.LoginConverter;
import com.yupay.lunatico.security.UserLoginRequest;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.Nullable;

/**
 * Dialog to allow the user to enter login and password.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUserLogin extends Dialog<UserLoginRequest> {
    /**
     * Text formatter for user login id.
     */
    private final TextFormatter<String> fmtLogin
            = LoginConverter.formatter();

    /**
     * FXML control injected from user-login.fxml
     */
    @FXML
    private DialogPane top;

    /**
     * FXML control injected from user-login.fxml
     */
    @FXML
    private TextField txtId;

    /**
     * FXML control injected from user-login.fxml
     */
    @FXML
    private PasswordField txtPassword;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setResultConverter(this::buildRequest);
        setDialogPane(top);
        setTitle("Inicio de Sesión");
        top.lookupButton(ButtonType.OK)
                .disableProperty()
                .bind(Bindings.createBooleanBinding(
                        this::infoFailed,
                        txtId.textProperty(),
                        txtPassword.textProperty()
                ));
        txtId.setTextFormatter(fmtLogin);
    }

    /**
     * Builds the login request to send to other layers
     * after checking if OK button has been selected.
     * Meant to be used as result converter.
     *
     * @return the built request.
     */
    private @Nullable UserLoginRequest buildRequest(ButtonType button) {
        return button == ButtonType.OK
                ? new UserLoginRequest(txtId.getText(), txtPassword.getText())
                : null;
    }

    /**
     * Checks if login information is failed, so OK
     * button keeps disabled.
     *
     * @return true if login shouldn't proceed.
     */
    private boolean infoFailed() {
        var strId = txtId.getText();
        var strPwd = txtPassword.getText();
        return strId == null || strId.isBlank()
                || strPwd == null || strPwd.isBlank();
    }
}
