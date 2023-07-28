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

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Static DAO factory for data access layer.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOFactory {

    /**
     * Hidden constructor to avoid instanciation.
     *
     * @throws IllegalAccessException always.
     */
    @Contract("->fail")
    private DAOFactory() throws IllegalAccessException {
        throw new IllegalAccessException("This is a static factory class.");
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.User}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOUserImpl user() {
        return new DAOUserImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.Warehouse}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOWarehouseImpl warehouse() {
        return new DAOWarehouseImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.Unit}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOUnitImpl unit() {
        return new DAOUnitImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.Item}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOItemImpl item() {
        return new DAOItemImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.TypeFolio}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOTypeFolioImpl typeFolio() {
        return new DAOTypeFolioImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.Person}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOPersonImpl person() {
        return new DAOPersonImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.Balance}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOBalanceImpl balance() {
        return new DAOBalanceImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.OvBalance}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOOvBalanceImpl balanceOverview() {
        return new DAOOvBalanceImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.KardexDetail}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOKardexDetailImpl kardexDetail() {
        return new DAOKardexDetailImpl();
    }

    /**
     * Creates a new instance from a given implementation.
     *
     * @return implementation for {@link com.yupay.lunatico.model.Movement}
     */
    @Contract(value = " -> new", pure = true)
    public static @NotNull DAOMovementImpl movement() {
        return new DAOMovementImpl();
    }
}
