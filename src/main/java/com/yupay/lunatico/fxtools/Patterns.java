/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

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
