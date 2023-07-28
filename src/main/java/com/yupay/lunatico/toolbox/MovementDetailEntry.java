/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.toolbox;

import com.yupay.lunatico.dao.DAOBalanceImpl;
import com.yupay.lunatico.model.Balance;
import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.MovementDetail;
import com.yupay.lunatico.model.Warehouse;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

/**
 * Record to hold pairs of detail lines and
 * item's historical balance while performing the
 * some movement operation.
 *
 * @param detail  the detail part.
 * @param item    the warehouse item part.
 * @param balance the balance part.
 * @author InfoYupay SACS
 * @version 1.0
 */
public record MovementDetailEntry(
        @NotNull MovementDetail detail,
        @NotNull Balance balance,
        @NotNull Item item) {
    /**
     * Fetches the historical balance for the given
     * detail object. This will rely upon
     * {@link  DAOBalanceImpl#fetchHistoricBalance(EntityManager, Warehouse, Item)}.
     * It also will refresh the detail's item from database using PESSIMISTIC_WRITE.
     *
     * @param detail    the movement detail line.
     * @param warehouse the warehouse at where the item is stored.
     * @param em        the entity manager object.
     * @param dao       the DAO object (this should be a reusable object).
     * @return a new warehouse stock from the database.
     */
    public static @NotNull MovementDetailEntry fetchBalanceForDetail(
            @NotNull MovementDetail detail,
            @NotNull Warehouse warehouse,
            @NotNull EntityManager em,
            @NotNull DAOBalanceImpl dao) {
        var itm = em.find(Item.class,
                detail.getItem().getId());
        var balance = dao.fetchHistoricBalance(em,warehouse,itm);
        return new MovementDetailEntry(detail,
                balance,
                itm);
    }

    /**
     * Checks if the local (historical) stock is enough
     * to process an output operation (sale, gift, etc).
     *
     * @return true if you can substract to the balance
     * the detail quantity.
     */
    public boolean canSubstract() {
        //return balanceUnits >= line quantity.
        return balance().getBalanceUnits()
                .compareTo(detail.getQuantity()) >= 0;
    }

    @Override
    public String toString() {
        return "Detail %02d Available stock: %.2f, Required stock: %.2f, Item: %s at Warehouse %s."
                .formatted(
                        detail.getLine(),
                        balance.getBalanceUnits(),
                        detail.getQuantity(),
                        detail.getItem().getName(),
                        balance().getWarehouse().getName());
    }
}
