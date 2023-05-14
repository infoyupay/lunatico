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
