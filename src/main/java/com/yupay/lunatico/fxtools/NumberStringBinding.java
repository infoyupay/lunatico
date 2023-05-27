package com.yupay.lunatico.fxtools;

import javafx.beans.Observable;
import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

/**
 * Binding to
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class NumberStringBinding<T extends Number> extends StringBinding {
    /**
     * The master observable value, it must extends Number.
     */
    @NotNull
    private final ObservableValue<T> master;
    /**
     * Decimal format, to be created with a pattern.
     */
    @NotNull
    private final DecimalFormat format;
    /**
     * The dependencies to observe, they may be from any other type.
     * This implementation won't update pattern or symbols.
     */
    private final Observable[] dependencies;

    /**
     * Full constructor.
     *
     * @param pattern      the pattern to use in the inner formatter.
     * @param symbols      the decimal format symbols to use.
     * @param master       the main observable (it must extend number).
     * @param dependencies the secondary dependencies to observe.
     */
    public NumberStringBinding(@NotNull String pattern,
                               @NotNull DecimalFormatSymbols symbols,
                               @NotNull ObservableObjectValue<T> master,
                               @Nullable Observable... dependencies) {
        this.master = master;
        this.format = new DecimalFormat(pattern, symbols);
        if (dependencies != null && dependencies.length > 0) {
            this.dependencies = Arrays.copyOf(dependencies, dependencies.length + 1);
            this.dependencies[dependencies.length] = master;
        } else {
            this.dependencies = new Observable[]{master};
        }
        bind(this.dependencies);
    }

    /**
     * Static factory to create a new instance for warehouse Lunatico app.
     *
     * @param master       the observable stuff.
     * @param dependencies secondary dependencies to observe.
     * @return a new, never null, binding.
     */
    @Contract("_, _ -> new")
    public static @NotNull NumberStringBinding<BigDecimal> forWarehouse(
            @NotNull ObservableObjectValue<BigDecimal> master,
            @Nullable Observable... dependencies) {
        return new NumberStringBinding<>(
                "#,##0.00000000",
                PeruvianLocalization.SYM_PERU,
                master,
                dependencies);
    } /**
     * Static factory to create a new instance for monetized values in Lunatico app.
     *
     * @param master       the observable stuff.
     * @param dependencies secondary dependencies to observe.
     * @return a new, never null, binding.
     */
    @Contract("_, _ -> new")
    public static @NotNull NumberStringBinding<BigDecimal> forMonetized(
            @NotNull ObservableObjectValue<BigDecimal> master,
            @Nullable Observable... dependencies) {
        return new NumberStringBinding<>(
                "¤ #,##0.00000000",
                PeruvianLocalization.SYM_PERU,
                master,
                dependencies);
    }

    @Override
    public ObservableList<Observable> getDependencies() {
        return (dependencies.length == 1) ?
                FXCollections.singletonObservableList(dependencies[0])
                : FXCollections.unmodifiableObservableList(
                FXCollections.observableArrayList(dependencies));
    }

    @Override
    protected String computeValue() {
        var val = master.getValue();
        if (val == null) return "";
        else return format.format(val);
    }

}
