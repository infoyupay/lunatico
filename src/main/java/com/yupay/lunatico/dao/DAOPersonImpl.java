package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Person;
import org.jetbrains.annotations.NotNull;

/**
 * DAO implementation for Person entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOPersonImpl implements DAO<Person> {
    @Override
    public @NotNull Class<Person> entity() {
        return Person.class;
    }
}
