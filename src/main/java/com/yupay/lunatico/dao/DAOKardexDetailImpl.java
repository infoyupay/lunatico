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
