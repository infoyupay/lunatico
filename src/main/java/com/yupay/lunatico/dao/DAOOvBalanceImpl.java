package com.yupay.lunatico.dao;

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
}