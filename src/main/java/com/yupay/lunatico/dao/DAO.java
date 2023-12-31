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

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.stream.Stream;

/**
 * The data access object abstraction.
 *
 * @param <T> type erasure of the persisted model entity.
 * @author InfoYupay SACS
 * @version 1.0
 */
public sealed interface DAO<T>
        permits DAOBalanceImpl,
        DAOItemImpl,
        DAOKardexDetailImpl,
        DAOMovementImpl,
        DAOOvBalanceImpl,
        DAOPersonImpl,
        DAOTypeFolioImpl,
        DAOUnitImpl,
        DAOUserImpl,
        DAOWarehouseImpl {
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

    /**
     * Fetches one given item by id.
     *
     * @param em  the entity manager object.
     * @param id  the id (primary key) value (never null).
     * @param <U> type erasure for the primary key.
     * @return the requested entity.
     */
    default @NotNull <U> T fetchOne(@NotNull EntityManager em, @NotNull U id) {
        return em.getReference(entity(), id);
    }
}
