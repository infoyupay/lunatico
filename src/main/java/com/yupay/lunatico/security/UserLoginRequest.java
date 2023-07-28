/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.security;

import org.jetbrains.annotations.NotNull;

/**
 * This is a wrapper to transport user and password information.
 *
 * @param user     the user login id.
 * @param password the user password.
 * @author InfoYupay SACS
 * @version 1.0
 */
public record UserLoginRequest(
        @NotNull String user,
        @NotNull String password
) {
}
