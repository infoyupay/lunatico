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
