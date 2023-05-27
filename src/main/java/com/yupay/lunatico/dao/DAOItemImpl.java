package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Item;
import org.jetbrains.annotations.NotNull;

/**
 * DAO implementation for Item entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOItemImpl implements DAO<Item> {
    /**
     * Package protected constructor.
     * To instanciate use {@link DAOFactory}
     */
    DAOItemImpl() {
    }

    @Override
    public @NotNull Class<Item> entity() {
        return Item.class;
    }
}
