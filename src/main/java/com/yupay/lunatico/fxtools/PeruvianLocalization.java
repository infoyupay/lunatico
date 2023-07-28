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

import java.text.DecimalFormatSymbols;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Currency;

/**
 * Static factory class for decimals.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class PeruvianLocalization {
    /**
     * Decimal format symbols for Peru.
     */
    public static final DecimalFormatSymbols SYM_PERU = new DecimalFormatSymbols() {
        @Override
        public char getGroupingSeparator() {
            return ',';
        }

        @Override
        public char getDecimalSeparator() {
            return '.';
        }

        @Contract(pure = true)
        @Override
        public @NotNull String getCurrencySymbol() {
            return "S/";
        }

        @Override
        public Currency getCurrency() {
            return Currency.getInstance("PEN");
        }

        @Override
        public char getMonetaryDecimalSeparator() {
            return '.';
        }

        @Override
        public char getMonetaryGroupingSeparator() {
            return ',';
        }
    };
    /**
     * Formatter for time stamps without timezone.
     */
    public static final DateTimeFormatter STAMP
            = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .appendLiteral('/')
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .appendLiteral('/')
            .appendValue(ChronoField.YEAR, 4)
            .appendLiteral(' ')
            .appendValue(ChronoField.CLOCK_HOUR_OF_DAY, 2)
            .appendLiteral(':')
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .appendLiteral(':')
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .appendLiteral('.')
            .appendValue(ChronoField.MILLI_OF_SECOND, 3)
            .toFormatter();
    /**
     * The System offset as a constant.
     */
    public static final ZoneOffset SYS_OFFSET = ZonedDateTime.now().getOffset();
}
