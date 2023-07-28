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

import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxPersonMV;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * Static factory to convert an item into a String (text).
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxEntityFormatters {
    /**
     * Creates a function to format an item as a String.
     *
     * @return a new function.
     */
    @Contract(pure = true)
    public static @NotNull Function<FxItemMV, String> forItem() {
        return x -> x == null ? "" : x.getId() + " : " + x.getName();
    }

    /**
     * Creates a function to format a person as a String.
     *
     * @return a new function.
     */
    @Contract(pure = true)
    public static @NotNull Function<FxPersonMV, String> forPerson() {
        return x -> {
            if (x == null) return "";
            if (x.getDoiType() != null
                    && x.getDoiNum() != null
                    && !x.getDoiNum().isBlank()) {
                return "(%s - %s) %s".formatted(
                        x.getDoiType(), x.getDoiNum(), x.getName());
            } else {
                return "(S.D.) " + x.getName();
            }
        };
    }
}
