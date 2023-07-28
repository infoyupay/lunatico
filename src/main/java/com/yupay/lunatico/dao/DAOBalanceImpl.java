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

import com.yupay.lunatico.model.Balance;
import com.yupay.lunatico.model.BalanceType;
import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.Warehouse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.stream.Stream;

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

    /**
     * Queries for all balances within a date range for an item.
     *
     * @param em    entity manager object.
     * @param item  the item which must be searched.
     * @param since the since date of balance snapshot (inclusive).
     * @param until the last date of balance snapshot (inclusive).
     * @return a new stream of results.
     */
    @Contract("_,_,_,_->new")
    public @NotNull Stream<Balance> queryBalances(
            @NotNull EntityManager em,
            @NotNull Item item,
            @NotNull LocalDate since,
            @NotNull LocalDate until) {
        return em.createQuery(
                        """
                                SELECT B
                                    FROM Balance B
                                    WHERE   (B.shotStamp >= ?1 AND B.shotStamp < ?2)
                                            AND B.item = ?4""",
                        Balance.class)
                .setParameter(1, since.atStartOfDay())
                .setParameter(2, until.plusDays(1).atStartOfDay())
                .setParameter(4, item)
                .getResultStream();
    }
}
