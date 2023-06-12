package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxforms.ErrorHandler;
import com.yupay.lunatico.fxforms.FxLunatico;
import com.yupay.lunatico.fxmview.FxWarehouseMV;
import com.yupay.lunatico.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * Flow to create opening balances.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxOpeningFlow {
    /**
     * Warehouse to which the opening record refers.
     */
    private Warehouse warehouse;
    /**
     * What to do after successful completion of the flow.
     */
    private Runnable afterSuccess;

    /**
     * Fluent setter - with.
     *
     * @param warehouse new value to set in {@link #warehouse}
     * @return this instance.
     */
    public final FxOpeningFlow withWarehouse(@NotNull FxWarehouseMV warehouse) {
        this.warehouse = warehouse.toModel();
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #warehouse}
     */
    public final Warehouse getWarehouse() {
        return warehouse;
    }

    /**
     * Fluent setter - with.
     *
     * @param afterSuccess new value to set in {@link #afterSuccess}
     * @return this instance.
     */
    public final FxOpeningFlow withAfterSuccess(@NotNull Runnable afterSuccess) {
        this.afterSuccess = afterSuccess;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #afterSuccess}
     */
    public final Runnable getAfterSuccess() {
        return afterSuccess;
    }

    /**
     * Starts the flow execution.
     */
    public void go() {
        EntityTransaction et = null;
        try (var em = DataSource.em()) {
            et = em.getTransaction();
            //begin transaction (acquire lock).
            et.begin();

            var daoBalance = DAOFactory.balance();

            //1. List all active items.
            DAOFactory.item()
                    .listActive(em)
                    //2. fetch or produce balance out from closing one.
                    .map(i -> fetchOrProduceBalance(i, em))
                    //3. Persist
                    .forEach(i -> daoBalance.insertOne(em, i));

            //commit transaction.
            et.commit();
            if (getAfterSuccess() != null) getAfterSuccess().run();
        } catch (RuntimeException e) {
            DataSource.checkAndRollback(et);
            new ErrorHandler()
                    .withBriefing("Ocurrió un error al crear el asiento de apertura diaria.")
                    .accept(e);
        }
    }

    /**
     * Fetches or produces a balance entry.
     * To fetch the entry, will search for the last
     * available closing entry and convert it into the
     * next opening entry.
     * If no closing entry matches, then a new one with
     * ZERO quantities and prices will be created.
     *
     * @param item the item to open.
     * @param em   the entity manager object.
     * @return the resulting balance entry.
     */
    @SuppressWarnings("DuplicatedCode")
    @Contract("_,_->new")
    private @NotNull Balance fetchOrProduceBalance(
            @NotNull Item item,
            @NotNull EntityManager em) {
        return DAOFactory.balance()
                //Fetches the last balance from database.
                .fetchLastBalance(em, item, getWarehouse(), BalanceType.CLOSURE)
                //If found, will make a copy.
                .map(b -> {
                    var r = new Balance();
                    r.setItem(item);
                    r.setWarehouse(getWarehouse());
                    r.setBalanceCost(b.getBalanceCost());
                    r.setBalanceUnitCost(b.getBalanceUnitCost());
                    r.setBalanceUnits(b.getBalanceUnits());
                    r.setDate(LocalDate.now());
                    r.setDetail(b.getDetail()
                            .parallelStream()
                            .map(dt -> {
                                var rdt = new BalanceDetail();
                                rdt.setBalance(r);
                                rdt.setCost(dt.getCost());
                                rdt.setQuantity(dt.getQuantity());
                                rdt.setSummaryType(dt.getSummaryType());
                                return rdt;
                            })
                            .toList());
                    r.setType(BalanceType.OPENING);
                    r.setUser(FxLunatico.APP_CONTROLLER.getLoggedUser().toModel());
                    if (r.getBalanceCost().compareTo(b.getBalanceCost()) != 0
                            || r.getBalanceUnitCost().compareTo(b.getBalanceUnitCost()) != 0
                            || r.getBalanceUnits().compareTo(b.getBalanceUnits()) != 0) {
                        throw new IllegalStateException("Item " + r.getItem().getId() + " balance inconsistent.");
                    }
                    return r;
                })
                //If not found, will create one from scratch.
                .orElseGet(() -> {
                    var r = new Balance();
                    r.setItem(item);
                    r.setWarehouse(getWarehouse());
                    r.setBalanceCost(item.getBalanceCost());
                    r.setBalanceUnitCost(item.getBalanceUnitCost());
                    r.setBalanceUnits(item.getBalanceUnits());
                    r.setDate(LocalDate.now());
                    r.setDetail(Arrays.stream(MovementType.values())
                            .map(dt -> {
                                var rdt = new BalanceDetail();
                                rdt.setBalance(r);
                                rdt.setCost(BigDecimal.ZERO);
                                rdt.setQuantity(BigDecimal.ZERO);
                                rdt.setSummaryType(dt);
                                return rdt;
                            })
                            .toList());
                    r.setType(BalanceType.OPENING);
                    r.setUser(FxLunatico.APP_CONTROLLER.getLoggedUser().toModel());
                    return r;
                });
    }
}
