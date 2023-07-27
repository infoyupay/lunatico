package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.TypeFolio;
import org.jetbrains.annotations.NotNull;

/**
 * DAO implementation for Types of Folio.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOTypeFolioImpl implements DAO<TypeFolio> {
    /**
     * Package private constructor,
     * use {@link DAOFactory#typeFolio()}
     */
    DAOTypeFolioImpl() {
    }

    @Override
    public @NotNull Class<TypeFolio> entity() {
        return TypeFolio.class;
    }
}
