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

import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.KardexDetail;
import com.yupay.lunatico.model.Warehouse;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.stream.Stream;

/**
 * The Kardex Detail DAO implementation.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class DAOKardexDetailImpl implements DAO<KardexDetail> {
    /**
     * Package-private constructor, use
     * {@link DAOFactory#kardexDetail()}
     */
    DAOKardexDetailImpl() {
    }

    @Override
    public @NotNull Class<KardexDetail> entity() {
        return KardexDetail.class;
    }

    /**
     * Lists all kardex details within a range of dates.
     *
     * @param from      the starting date (inclusive).
     * @param until     the ending date (inclusive).
     * @param item      the item to query.
     * @param warehouse the warehouse.
     * @param em        the entity manager object.
     * @return a stream with found details.
     */
    public Stream<KardexDetail> listWithinDates(
            @NotNull LocalDate from,
            @NotNull LocalDate until,
            @NotNull Item item,
            @NotNull Warehouse warehouse,
            @NotNull EntityManager em
    ) {
        return em.createQuery("SELECT K FROM KardexDetail K WHERE K.warehouse = ?1 " +
                                "AND K.item = ?2 AND K.docDate >= ?3 AND K.docDate <= ?4",
                        entity())
                .setParameter(1, warehouse.getId())
                .setParameter(2, item.getId())
                .setParameter(3, from)
                .setParameter(4, until)
                .getResultStream();
    }
}
