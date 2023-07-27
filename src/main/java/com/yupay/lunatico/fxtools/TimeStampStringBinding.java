package com.yupay.lunatico.fxtools;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.time.temporal.Temporal;

/**
 * Binding to format a Temporal as a TimeStamp.
 *
 * @author InfoYupay SACS
 * @version 1.0
 * @param <T> type erasure for the converted value.
 */
public class TimeStampStringBinding<T extends Temporal> extends StringBinding {
    /**
     * The observable temporal to observe.
     */
    @NotNull
    private final ObservableObjectValue<T> master;

    /**
     * Full constructor.
     *
     * @param master the observable temporal to watch.
     */
    public TimeStampStringBinding(@NotNull ObservableObjectValue<T> master) {
        this.master = master;
    }

    @Override
    public ObservableList<?> getDependencies() {
        return FXCollections.singletonObservableList(master);
    }

    @Override
    protected String computeValue() {
        var val = master.getValue();
        if (val == null) return "";
        else return PeruvianLocalization.STAMP.format(val);
    }
}
