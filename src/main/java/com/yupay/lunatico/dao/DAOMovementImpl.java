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
