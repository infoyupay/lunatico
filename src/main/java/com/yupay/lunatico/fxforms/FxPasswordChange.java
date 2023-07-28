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

import com.yupay.lunatico.security.PasswordChangeRequest;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.util.Callback;

import java.util.Objects;

import static javafx.beans.binding.Bindings.createBooleanBinding;

/**
 * Password change dialog.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxPasswordChange extends Dialog<PasswordChangeRequest>
        implements Callback<ButtonType, PasswordChangeRequest> {


    /**
     * Require confirmation flag, if true confirm password
     * textbox will be enabled.
     */
    private final BooleanProperty requireOldPassword =
            new SimpleBooleanProperty(this, "requireOldPassword", true);

    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private DialogPane top;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private PasswordField txtConfirmPassword;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private PasswordField txtNewPassword;
    /**
     * FXML control injected from user-view.fxml
     */
    @FXML
    private PasswordField txtOldPassword;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        setResultConverter(this);
        setDialogPane(top);
        setTitle("Cambio de Contraseña");
        top.lookupButton(ButtonType.OK)
                .disableProperty()
                .bind(createBooleanBinding(
                        this::passwordFailed,
                        txtConfirmPassword.textProperty(),
                        txtNewPassword.textProperty()));
    }

    @Override
    public PasswordChangeRequest call(ButtonType b) {
        if (b != ButtonType.OK) return null;
        return new PasswordChangeRequest()
                .withNewPassword(txtNewPassword.getText())
                .withOldPassword(txtOldPassword.getText());
    }

    /**
     * Checks if new password and password confirmation matches.
     * Also will check that the new password is not blank and
     * has a minimum 8 chars length.
     *
     * @return true if failed, false if passed, so OK button will be available.
     */
    private boolean passwordFailed() {
        var str = txtNewPassword.getText();
        if (str == null || str.isBlank() || str.length() < 8) return true;
        return !Objects.equals(str, txtConfirmPassword.getText());
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #requireOldPassword}.get();
     */
    public final boolean isRequireOldPassword() {
        return requireOldPassword.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param requireOldPassword value to assign into {@link #requireOldPassword}.
     */
    public final void setRequireOldPassword(boolean requireOldPassword) {
        this.requireOldPassword.set(requireOldPassword);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #requireOldPassword}.
     */
    @SuppressWarnings("unused")
    public final BooleanProperty requireOldPasswordProperty() {
        return requireOldPassword;
    }


}
