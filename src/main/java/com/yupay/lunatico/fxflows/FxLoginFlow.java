/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.EasyAlert;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxmview.FxUserMV;
import com.yupay.lunatico.model.User;
import com.yupay.lunatico.security.UserLoginRequest;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Workflow to allow the user to log in into the system.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxLoginFlow {
    /**
     * Shows the user a dialog to login, and then
     * processes the login information. If user can
     * be authenticated
     *
     * @param onSuccess what to do if login succeeds.
     */
    public void login(@NotNull Consumer<FxUserMV> onSuccess) {
        try {
            var rq = FxForms.userLogin().showAndWait();
            if (rq.isEmpty()) return;
            rq.flatMap(this::sendRequest)
                    .map(FxUserMV::new)
                    .ifPresentOrElse(onSuccess.andThen(u ->
                                    EasyAlert.info()
                                            .withTitle("Bienvenido")
                                            .withHeaderText("Hola " + u.getRealName())
                                            .withContentText("Se inició sesión correctamente.")
                                            .run()
                            ),
                            EasyAlert.error()
                                    .withTitle("Error")
                                    .withHeaderText("La combinación de usuario y contraseña no existe.")
                                    .withContentText("Por favor, verifique su contraseña y vuelva a intentar.")
                                    .buttonOkOnly());
        } catch (Throwable e) {
            new ErrorHandler()
                    .withBriefing("No se pudo iniciar sesión con los datos de usuario.")
                    .accept(e);
        }
    }

    /**
     * Authenticates user in the Data access layer.
     *
     * @param request the login request object.
     * @return logged-in user.
     */
    @Contract("_->new")
    private @NotNull Optional<User> sendRequest(
            @NotNull UserLoginRequest request) {
        try (var em = DataSource.em()) {
            return DAOFactory.user().authenticateUser(
                    em, request.user(), request.password());
        }
    }
}
