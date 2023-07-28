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
import com.yupay.lunatico.model.ItemType;
import com.yupay.lunatico.model.MovementType;
import com.yupay.lunatico.model.VirtualWarehouseType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Static factory class to create observable lists of enumerations,
 * in order to use them in fxml fx:factory.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class FxEnumCollections {
    /**
     * Virtual warehouse types.
     *
     * @return the warehouse types in an observable list.
     */
    @Contract(" -> new")
    public static @NotNull ObservableList<VirtualWarehouseType> virtualWarehouseTypes() {
        return FXCollections.observableArrayList(VirtualWarehouseType.values());
    }

    /**
     * Types of warehouse items.
     *
     * @return the item types in an observable list.
     */
    @Contract(" -> new")
    public static @NotNull ObservableList<ItemType> itemTypes() {
        return FXCollections.observableArrayList(ItemType.values());
    }

    /**
     * Types of Document Of Id.
     *
     * @return the item types in an observable list.
     */
    @Contract(" -> new")
    public static @NotNull ObservableList<DoiType> doiTypes() {
        return FXCollections.observableArrayList(DoiType.values());
    }

    /**
     * Types of Document Of Id.
     *
     * @return the item types in an observable list.
     */
    @Contract(" -> new")
    public static @NotNull ObservableList<MovementType> movementTypes() {
        return FXCollections.observableArrayList(MovementType.values());
    }
}
