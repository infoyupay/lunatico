package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Balance;
import com.yupay.lunatico.model.BalanceType;
import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.Warehouse;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * DAO implementation for Balances.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOBalanceImpl implements DAO<Balance> {
    /**
     * Package-private constructor.
     * Use the static factory at {@link DAOFactory#balance()}
     */
    DAOBalanceImpl() {
    }

    @Override
    public @NotNull Class<Balance> entity() {
        return Balance.class;
    }

    /**
     * Searches and fetches the last balance (ordered by date)
     * for a given item, type and warehouse.
     *
     * @param em        entity manager object.
     * @param item      the item to find.
     * @param warehouse the warehouse to find.
     * @param type      the type of balance to find.
     * @return an optional with found element. May be empty.
     */
    public @NotNull Optional<Balance> fetchLastBalance(
            @NotNull EntityManager em,
            @NotNull Item item,
            @NotNull Warehouse warehouse,
            @NotNull BalanceType type) {
        return em.createQuery(
                        "SELECT B FROM Balance B " +
                                "WHERE B.type = ?1 " +
                                "AND B.item = ?2 " +
                                "AND B.warehouse = ?3 " +
                                "ORDER BY B.date DESC",
                        entity())
                .setMaxResults(1)
                .setParameter(1, type)
                .setParameter(2, item)
                .setParameter(3, warehouse)
                .getResultStream()
                .findAny();
    }
}
