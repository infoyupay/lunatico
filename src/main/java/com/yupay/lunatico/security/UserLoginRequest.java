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
