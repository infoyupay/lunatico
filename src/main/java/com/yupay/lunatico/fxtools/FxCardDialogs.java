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

import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxPersonMV;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

/**
 * Static factory class to create new {@link CardDialog},
 * this is intended to use as fx:factory in the fxml files.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxCardDialogs {
    /**
     * Creates a card dialog for item entities.
     *
     * @return a new card dialog supplier.
     */
    @Contract(" -> new")
    public static @NotNull Supplier<CardDialog<FxItemMV>> forItem() {
        return FxForms::itemCard;
    }

    /**
     * Creates a card dialog for person entities.
     *
     * @return a new card dialog supplier.
     */
    @Contract(" -> new")
    public static @NotNull Supplier<CardDialog<FxPersonMV>> forPerson() {
        return FxForms::personCard;
    }
}
