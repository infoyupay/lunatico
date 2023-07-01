package com.yupay.lunatico.fxtools;

import com.yupay.lunatico.fxmview.FxFolioTypeMV;
import com.yupay.lunatico.fxmview.FxWarehouseMV;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * String converter implementation to use in combo boxes.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class FromListStringConverter<T> extends StringConverter<T> {
    /**
     * The combo box where this converter is used.
     */
    @NotNull
    private final ComboBox<T> comboBox;

    /**
     * Full constructor.
     *
     * @param comboBox the combo box where this converter will be used.
     */
    public FromListStringConverter(@NotNull ComboBox<T> comboBox) {
        this.comboBox = comboBox;
    }

    /**
     * Static factory to create a new converter to use in warehouse items.
     *
     * @param comboBox the combo box where this converter shall be used.
     * @return a new converter.
     */
    @Contract("_ -> new")
    public static @NotNull FromListStringConverter<FxWarehouseMV>
    forWarehouse(ComboBox<FxWarehouseMV> comboBox) {
        return new FromListStringConverter<>(comboBox) {
            @Override
            protected @NotNull String matcher(@NotNull FxWarehouseMV item) {
                return item.getName();
            }
        };
    }

    /**
     * Static factory to create a new converter to use in folio type items.
     *
     * @param comboBox the combo box where this converter shall be used.
     * @return a new converter.
     */
    @Contract("_ -> new")
    public static @NotNull FromListStringConverter<FxFolioTypeMV>
    forFolioType(ComboBox<FxFolioTypeMV> comboBox) {
        return new FromListStringConverter<>(comboBox) {
            @Override
            protected @NotNull String matcher(@NotNull FxFolioTypeMV item) {
                return item.getName();
            }
        };
    }

    /**
     * Extracts a certain string from a given item to be matched
     * against text search.
     *
     * @param item the item whose string value should be extracted.
     * @return a mather text.
     */
    protected abstract @NotNull String matcher(@NotNull T item);

    @Override
    public String toString(T object) {
        return Objects.toString(object, "");
    }

    @Override
    public T fromString(String string) {
        if (string != null && !string.isBlank()) {
            var ptn = Patterns.containsCI(string.strip());
            for (var x : comboBox.getItems()) {
                if (ptn.matcher(matcher(x)).matches()) {
                    return x;
                }
            }
        }
        return null;
    }
}
