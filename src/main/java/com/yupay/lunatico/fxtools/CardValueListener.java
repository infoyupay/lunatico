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
