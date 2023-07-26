package com.yupay.lunatico.fxflows;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.model.Balance;
import com.yupay.lunatico.model.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Flow to generate data series for the item trend query.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxItemTrendFlow {

    /**
     * The inclusive first date for the trend indexing.
     */
    private LocalDate since;
    /**
     * The inclusive last date for the trend indexing.
     */
    private LocalDate until;
    /**
     * Warehouse item to check for.
     */
    private FxItemMV item;

    /**
     * Fluent setter - with.
     *
     * @param since new value to set in {@link #since}
     * @return this instance.
     */
    public final FxItemTrendFlow withSince(LocalDate since) {
        this.since = since;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #since}
     */
    public final LocalDate getSince() {
        return since;
    }

    /**
     * Fluent setter - with.
     *
     * @param until new value to set in {@link #until}
     * @return this instance.
     */
    public final FxItemTrendFlow withUntil(LocalDate until) {
        this.until = until;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #until}
     */
    public final LocalDate getUntil() {
        return until;
    }

    /**
     * Fluent setter - with.
     *
     * @param item new value to set in {@link #item}
     * @return this instance.
     */
    public final FxItemTrendFlow withItem(FxItemMV item) {
        this.item = item;
        return this;
    }

    /**
     * Accessor - getter.
     *
     * @return value of {@link #item}
     */
    public final FxItemMV getItem() {
        return item;
    }

    /**
     * Recollects data from database and redirects it
     * to a Chart series using the given consumer.
     *
     * @param onSuccess the given consumer.
     */
    public void go(@NotNull Consumer<ObservableList<Series<LocalDateTime, BigDecimal>>> onSuccess) {
        ObservableList<Series<LocalDateTime, BigDecimal>> series;
        try (var em = DataSource.em()) {
            //Work with series.
            series = DAOFactory.warehouse()
                    .listAll(em)
                    .map(this::toSeries)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
            //Work with balances.
            DAOFactory.balance()
                    .queryBalances(
                            em,
                            getItem().toModel(),
                            getSince(),
                            getUntil())
                    .map(this::toData)
                    .forEach(d -> series.stream()
                            .filter(s -> Objects.equals(s.getName(), d.getExtraValue()))
                            .findAny()
                            .ifPresent(s -> s.getData().add(d)));
        }
        onSuccess.accept(series);
    }

    /**
     * Convenient method to map a warehouse and create a new serie
     * for such a warehouse.
     *
     * @param warehouse the warehouse entity.
     * @return the created series.
     */
    @Contract("_->new")
    private @NotNull Series<LocalDateTime, BigDecimal>
    toSeries(@NotNull Warehouse warehouse) {
        var r = new Series<LocalDateTime, BigDecimal>();
        r.setName(warehouse.getName());
        r.setData(FXCollections.observableArrayList());
        return r;
    }

    /**
     * Convenient method to map a Balance object
     * into a chart Data.
     *
     * @param balance the balance with the information.
     * @return the data object.
     */
    private @NotNull Data<LocalDateTime, BigDecimal>
    toData(@NotNull Balance balance) {
        return new Data<>(balance.getShotStamp(),
                balance.getBalanceUnits(),
                balance.getWarehouse().getName());
    }
}
