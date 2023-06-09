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
