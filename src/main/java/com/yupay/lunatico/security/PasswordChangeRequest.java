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

/**
 * This class represents a password change request.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class PasswordChangeRequest {
    /**
     * User whose password should be changed.
     */
    private String user;
    /**
     * The old password. If not set,
     * a forced password reset should
     * be assumed.
     */
    private String oldPassword;
    /**
     * The new password.
     */
    private String newPassword;

    /**
     * Fluent setter - with.
     *
     * @param user new value to set in {@link #user}
     * @return this instance.
     */
    public PasswordChangeRequest withUser(String user) {
        this.user = user;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #user}
     */
    public String getUser() {
        return user;
    }

    /**
     * Fluent setter - with.
     *
     * @param oldPassword new value to set in {@link #oldPassword}
     * @return this instance.
     */
    public PasswordChangeRequest withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #oldPassword}
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Fluent setter - with.
     *
     * @param newPassword new value to set in {@link #newPassword}
     * @return this instance.
     */
    public PasswordChangeRequest withNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #newPassword}
     */
    public String getNewPassword() {
        return newPassword;
    }

}
