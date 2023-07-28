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

import javafx.beans.property.ListProperty;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Facility to clear components to default values.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class ClearFacility {
    /**
     * List of elements to register.
     */
    private final List<Object> elements = new ArrayList<>();

    /**
     * Registers a control, property or formatter.
     *
     * @param x   the element to add.
     * @param <T> type erasure of element.
     * @return this instance.
     */
    @Contract("_->this")
    public @NotNull <T> ClearFacility add(@NotNull T x) {
        elements.add(x);
        return this;
    }

    /**
     * Registers many controls, properties or formatters.
     *
     * @param x the elemets to add.
     * @return this instance.
     */
    public @NotNull ClearFacility add(@NotNull Object... x) {
        elements.addAll(Arrays.asList(x));
        return this;
    }

    /**
     * Clears all registered controls.
     */
    public void clear() {
        elements.forEach(x -> {
            if (x instanceof TextFormatter<?> fmt) fmt.setValue(null);
            else if (x instanceof SearchableTextField<?, ?> srch) srch.setValue(null);
            else if (x instanceof TextInputControl txt) txt.clear();
            else if (x instanceof ComboBox<?> cbo) cbo.setValue(null);
            else if (x instanceof CheckBox chk) chk.setSelected(false);
            else if (x instanceof Labeled lbl) lbl.setText("");
            else if (x instanceof TableView<?> tbl) tbl.setItems(FXCollections.observableArrayList());
            else if (x instanceof ListProperty<?> ls) ls.set(FXCollections.observableArrayList());
            else if (x instanceof Property<?> prop) prop.setValue(null);
        });
    }
}
