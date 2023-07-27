package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Warehouse;
import org.jetbrains.annotations.NotNull;

/**
 * DAO implementation to access Warehouse entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOWarehouseImpl implements DAO<Warehouse> {
    /**
     * Package-private constructor.
     * Use {@link DAOFactory#warehouse()}
     */
    DAOWarehouseImpl() {
    }

    @Override
    public @NotNull Class<Warehouse> entity() {
        return Warehouse.class;
    }
}
