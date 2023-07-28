/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The model view is an intermediary object between
 * the model layer and user experience / user interface.
 * This abstraction will help to make the ModelView
 * pattern work.
 *
 * @param <T> type erasure for model class.
 * @param <U> type erasure for model view implementation.
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class ModelView<T, U extends ModelView<T, U>> {
    /**
     * Constructor to copy information from a model entity.
     *
     * @param model the model entity.
     */
    @SuppressWarnings("unused")
    public ModelView(@NotNull T model) {

    }

    /**
     * Default no-op constructor.
     */
    public ModelView() {
    }

    /**
     * Creates a deep copy of this.
     *
     * @return never null.
     */
    @Contract("->new")
    public abstract @NotNull U deepCopy();

    /**
     * Creates a model entity based on this' information.
     *
     * @return the model entity.
     */
    @Contract("->new")
    public abstract @NotNull T toModel();

    /**
     * Copies information from the model entity.
     *
     * @param model the "source" model entity.
     */
    public abstract void fromModel(@NotNull T model);
}
