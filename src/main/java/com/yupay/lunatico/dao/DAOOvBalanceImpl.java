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

import com.yupay.lunatico.model.BalanceType;
import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.OvBalance;
import com.yupay.lunatico.model.Warehouse;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

/**
 * DAO implementation for balance overviews.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOOvBalanceImpl implements DAO<OvBalance> {
    /**
     * Package - protected, use {@link DAOFactory#balanceOverview()}
     */
    DAOOvBalanceImpl() {
    }

    @Override
    public @NotNull Class<OvBalance> entity() {
        return OvBalance.class;
    }

    /**
     * Lists all balance overviews at a given warehouse.
     *
     * @param em        the entity manager.
     * @param warehouse given warehouse.
     * @return a new stream from database.
     */
    @Contract("_,_->new")
    public @NotNull Stream<OvBalance> listAtWarehouse(@NotNull EntityManager em,
                                                      @NotNull Warehouse warehouse) {
        return em.createQuery(
                        "SELECT v FROM OvBalance v WHERE v.warehouse = ?1"
                        , entity())
                .setParameter(1, warehouse.getId())
                .getResultStream();
    }

    /**
     * Fetches one balance overview.
     *
     * @param em        the entity manager.
     * @param warehouse the warehouse.
     * @param item      the item.
     * @return the only historical balance overview.
     */
    public @NotNull OvBalance fetchOne(@NotNull EntityManager em,
                                       @NotNull Warehouse warehouse,
                                       @NotNull Item item) {
        return em.createQuery("SELECT v FROM OvBalance v WHERE v.itemId = ?1 AND v.warehouse = ?2",
                        entity())
                .setParameter(1, item.getId())
                .setParameter(2, warehouse.getId())
                .getSingleResult();
    }
}
