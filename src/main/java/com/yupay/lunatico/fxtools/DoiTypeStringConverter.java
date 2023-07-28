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

import com.yupay.lunatico.model.DoiType;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * String converter implementation to convert from DoiType to String
 * and vice versa based upon first string character, in such way that
 * any input starting with D will result in {@link DoiType#ID_CARD},
 * with R in {@link DoiType#TAX_ID} and with O in {@link DoiType#OTHERS}.
 * The input is case insensitive, and the opposite operation (toString)
 * just will invoke toString method.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class DoiTypeStringConverter extends StringConverter<DoiType> {
    /**
     * Static factory for FXML usage.
     *
     * @return just a new instance.
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DoiTypeStringConverter fxml() {
        return new DoiTypeStringConverter();
    }

    @Override
    public String toString(DoiType object) {
        return Objects.toString(object, "");
    }

    @Override
    public DoiType fromString(String string) {
        if (string == null || string.isBlank()) {
            return null;
        } else {
            return switch (string.strip().toUpperCase().charAt(0)) {
                case 'D' -> DoiType.ID_CARD;
                case 'R' -> DoiType.TAX_ID;
                case 'O' -> DoiType.OTHERS;
                default -> null;
            };
        }
    }
}
