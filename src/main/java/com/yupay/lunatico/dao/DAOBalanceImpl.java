package com.yupay.lunatico.dao;

import com.yupay.lunatico.model.Balance;
import com.yupay.lunatico.model.BalanceType;
import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.Warehouse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.jetbrains.annotations.NotNull;

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
     * Fetches the historical balance for given item
     * at a given warehouse.
     *
     * @param em        the entity manager object.
     * @param warehouse the warehouse at where the item is stored.
     * @param item      the item whose balance should be fetched.
     * @return the balance.
     */
    public @NotNull Balance fetchHistoricBalance(
            @NotNull EntityManager em,
            @NotNull Warehouse warehouse,
            @NotNull Item item) {
        return em.createQuery(
                        "SELECT B FROM Balance B WHERE " +
                                "B.item = ?1 AND " +
                                "B.warehouse = ?2 AND " +
                                "B.type = ?3",
                        Balance.class)
                .setParameter(1, item)
                .setParameter(2, warehouse)
                .setParameter(3, BalanceType.HISTORY)
                .getResultStream()
                .findAny()
                .orElseThrow(() -> new PersistenceException(
                        "BROKEN SYSTEM STATE, SINCE ALL ITEMS MUST HAVE " +
                                "A VALID HISTORIC BALANCE FOR ANY GIVEN " +
                                "WAREHOUSE OR ITEM AT ANY TIME."));
    }
}
