package com.yupay.lunatico.dao;

import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

/**
 * The data access object abstraction.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public sealed interface DAO<T> permits DAOUserImpl {
    /**
     * The entity class representation.
     *
     * @return a never null Class object.
     */
    @NotNull Class<T> entity();

    /**
     * Persists one given item.
     *
     * @param em   the entity manager.
     * @param item the given item.
     */
    default void insertOne(@NotNull EntityManager em,
                           @NotNull T item) {
        em.persist(item);
    }

}
