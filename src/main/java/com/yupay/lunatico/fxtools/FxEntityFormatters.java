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
