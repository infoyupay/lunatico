package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Unit;
import org.jetbrains.annotations.NotNull;
/**
 * DAO implementation for measurement units.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOUnitImpl implements DAO<Unit> {

    /**
     * Package-private constructor. Use {@link DAOFactory}
     */
    DAOUnitImpl() {
    }


    @Override
    public @NotNull Class<Unit> entity() {
        return Unit.class;
    }
}
