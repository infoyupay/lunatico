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

import javafx.scene.control.Dialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Abstraction of the dialog value operations.
 *
 * @param <T> type erasure of model view item.
 * @author InfoYupay SACS
 * @version 1.0
 */
public interface CardDialog<T> {
    /**
     * Creates a new item (in blank) with default values.
     *
     * @return a new item.
     */
    @Contract("->new")
    @NotNull T blank();

    /**
     * Sets the editing value of the form.
     *
     * @param value the new editing value.
     */
    void setValue(@NotNull T value);

    /**
     * Sets the form editor mode.
     *
     * @param editorMode the editor mode.
     */
    void setFormMode(@NotNull EditorMode editorMode);

    /**
     * Gets the root element as a Dialog object.
     *
     * @return the dialog object (this).
     */
    @Contract("->this")
    @NotNull Dialog<T> getRoot();

    /**
     * Converts this dialog in the child of an owner.
     *
     * @param owner the parent/owner of this dialog form.
     */
    default void childOf(@Nullable Stage owner) {
        if (owner != null) {
            getRoot().initOwner(owner);
            getRoot().initModality(Modality.APPLICATION_MODAL);
        }
    }

    /**
     * Shows and wait for user interaction making this
     * dialog a creator card form, so a new item will be captured.
     *
     * @param owner the owner of the card form.
     * @return the result of {@link Dialog#showAndWait()}
     */
    default Optional<T> creator(@Nullable Stage owner) {
        childOf(owner);
        setValue(blank());
        setFormMode(EditorMode.CREATOR);
        return getRoot().showAndWait();
    }

    /**
     * Shows and wait for user interaction making this
     * dialog a creator card form. An existing item will be modified.
     *
     * @param owner the owner of the card form.
     * @param value the value to edit.
     * @return the result of {@link Dialog#showAndWait()}
     */
    default Optional<T> editor(@Nullable Stage owner,
                               @NotNull T value) {
        childOf(owner);
        setValue(value);
        setFormMode(EditorMode.EDITOR);
        return getRoot().showAndWait();
    }

    /**
     * Shows the user interaction making this
     * dialog a viewer card form. An existing item
     * will be shown but no modification should be allowed.
     *
     * @param owner the owner of the card form.
     * @param value the value to show.
     */
    default void viewer(@Nullable Stage owner,
                        @NotNull T value) {
        childOf(owner);
        setValue(value);
        setFormMode(EditorMode.VIEWER);
        getRoot().show();
    }
}
