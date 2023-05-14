package com.yupay.lunatico.fxtools;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.UNICODE_CASE;

/**
 * Static factory for patterns.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class Patterns {
    /**
     * Factory for a starts with element (case insensitive).
     *
     * @param text the text to find.
     * @return compiled pattern.
     */
    @Contract("_->new")
    public static @NotNull Pattern startsWithCI(@NotNull String text) {
        return Pattern.compile(
                "^" + Pattern.quote(text) + ".*",
                CASE_INSENSITIVE | UNICODE_CASE);
    } /**
     * Factory for a contains element (case insensitive).
     *
     * @param text the text to find.
     * @return compiled pattern.
     */
    @Contract("_->new")
    public static @NotNull Pattern containsCI(@NotNull String text) {
        return Pattern.compile(
                ".*" + Pattern.quote(text) + ".*",
                CASE_INSENSITIVE | UNICODE_CASE);
    }
}
