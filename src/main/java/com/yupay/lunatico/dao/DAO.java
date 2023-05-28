package com.yupay.lunatico.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.stream.Stream;

/**
 * The data access object abstraction.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public sealed interface DAO<T> permits DAOTypeFolioImpl, DAOItemImpl, DAOUnitImpl, DAOUserImpl, DAOWarehouseImpl {
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

    /**
     * Select * from ENTITY
     *
     * @param em the entity manager object.
     * @return the stream of all retrieved entities.
     */
    @NotNull
    @Unmodifiable
    default Stream<T> listAll(@NotNull EntityManager em) {
        return list(em, null);
    }

    /**
     * Select * from ENTITY WHERE active
     *
     * @param em the entity manager object.
     * @return the stream of all retrieved entities.
     */
    @NotNull
    @Unmodifiable
    default Stream<T> listActive(@NotNull EntityManager em) {
        var cb = em.getCriteriaBuilder();
        var qry = cb.createQuery(entity());
        var rt = qry.from(entity());
        qry.where(rt.get("active"));
        return em.createQuery(qry).getResultStream();
    }

    /**
     * Select * from ENTITY where FILTER
     *
     * @param em     the entity manager object.
     * @param filter the filter predicate for the where clause.
     *               If null, no WHERE clause will be used.
     * @return the stream of all retrieved entities.
     */
    @NotNull
    default Stream<T> list(@NotNull EntityManager em,
                           @Nullable Predicate filter) {
        var cb = em.getCriteriaBuilder();
        var qry = cb.createQuery(entity());
        qry.from(entity());
        if (filter != null) qry.where(filter);
        return em.createQuery(qry).getResultStream();
    }

    /**
     * This will invoke the merge method in the entity manager object.
     * This will update an existing item.
     *
     * @param em   the entity manager.
     * @param item the item to merge.
     * @return merged item.
     */
    default @NotNull T updateOne(@NotNull EntityManager em,
                                 @NotNull T item) {
        return em.merge(item);
    }
}
