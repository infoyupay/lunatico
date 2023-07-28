/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Movement;
import org.jetbrains.annotations.NotNull;

/**
 * The DAO implementation for Movement.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOMovementImpl implements DAO<Movement> {
    /**
     * Package-private constructor, use
     * {@link DAOFactory#movement()}
     */
    DAOMovementImpl() {
    }

    @Override
    public @NotNull Class<Movement> entity() {
        return Movement.class;
    }
}
