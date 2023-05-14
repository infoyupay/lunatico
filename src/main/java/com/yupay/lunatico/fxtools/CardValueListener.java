package com.yupay.lunatico.fxtools;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.NotNull;

/**
 * A change listener sub-class that expects a change event,
 * when the value is changed checks if old value is not null,
 * unbinds, then clear and last binds the new value to the UI.
 *
 * @param <T> type erasure of model view entity.
 * @author InfoYupay SACS
 * @version 1.0
 */
public interface CardValueListener<T> extends ChangeListener<T> {
    /**
     * Binds the given value to the UI controls.
     *
     * @param value the given value.
     */
    void bind(@NotNull T value);

    /**
     * Clears the UI controls.
     */
    void clear();

    /**
     * Unbinds the given value from the UI controls.
     *
     * @param value the given value.
     */
    void unbind(@NotNull T value);

    @Override
    default void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
        if (oldValue != null) unbind(oldValue);
        clear();
        if (newValue != null) bind(newValue);
    }
}
