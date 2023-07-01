package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.fxforms.FxLunatico;
import com.yupay.lunatico.model.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * Convenient static factory for some entities.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class EntitiesFactory {
    /**
     * Private constructor ensuring that not even
     * by reflection API this can be instanciated.
     *
     * @throws IllegalAccessException always.
     */
    @Contract("->fail")
    private EntitiesFactory() throws IllegalAccessException {
        throw new IllegalAccessException("Never instanciate a static factory class.");
    }

    /**
     * Creates a stream of historical balances for every available warehouse.
     *
     * @param item       the item for which balances are meant to be created.
     * @param warehouses the stream with all available warehouses.
     * @return the balances stream.
     */
    public static @NotNull Stream<Balance> historicalBalances(
            @NotNull Item item,
            @NotNull Stream<Warehouse> warehouses) {
        return warehouses.map(w -> historicalBalance(item, w));
    }

    /**
     * Creates a stream of historical balances for every available warehouse.
     *
     * @param items     the stream with all available items.
     * @param warehouse the warehouse where the items will be stored.
     * @return the balances stream.
     */
    public static @NotNull Stream<Balance> historicalBalancesForWarehouse(
            @NotNull Stream<Item> items,
            @NotNull Warehouse warehouse) {
        return items.map(i -> historicalBalance(i, warehouse));
    }

    /**
     * Static factory for a single historical balance for a given item
     * at a given warehouse unit.
     * In order to avoid balances id exhautions the helpers won't have
     * detail, since such a thing wouldn't provide any useful information nor
     * is part of the subsequent flows.
     *
     * @param item      the item for which the balance is meant to be created.
     * @param warehouse the warehouse unit where the item is located.
     * @return a new balance entity.
     */
    @Contract("_,_->new")
    public static @NotNull Balance historicalBalance(@NotNull Item item,
                                                     @NotNull Warehouse warehouse) {
        var r = new Balance();
        r.setBalanceCost(BigDecimal.ZERO);
        r.setBalanceUnitCost(BigDecimal.ZERO);
        r.setBalanceUnits(BigDecimal.ZERO);
        r.setShotStamp(LocalDateTime.now());
        r.setItem(item);
        r.setType(BalanceType.HISTORY);
        r.setUser(FxLunatico.APP_CONTROLLER.getLoggedUser().toModel());
        r.setWarehouse(warehouse);
        r.setDetail(MovementType.stream()
                .map(mt -> {
                    var ln = new BalanceDetail();
                    ln.setBalance(r);
                    ln.setCost(BigDecimal.ZERO);
                    ln.setQuantity(BigDecimal.ZERO);
                    ln.setSummaryType(mt);
                    return ln;
                }).toList());
        return r;
    }

}
