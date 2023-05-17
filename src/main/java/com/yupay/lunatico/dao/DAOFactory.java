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
}
