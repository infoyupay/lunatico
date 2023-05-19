package com.yupay.lunatico.fxtools;

import com.yupay.lunatico.model.ItemType;
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
}
