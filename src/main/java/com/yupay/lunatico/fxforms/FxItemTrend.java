package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxflows.FxItemTrendFlow;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxtools.FxTimestampAxis;
import com.yupay.lunatico.fxtools.SearchableTextField;
import com.yupay.lunatico.model.Item;
import javafx.fxml.FXML;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Master view controller for the item-trend.fxml form.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxItemTrend extends MasterView {

    /**
     * FXML control injected from item-trend.fxml
     */
    @FXML
    private FxTimestampAxis axisDate;

    /**
     * FXML control injected from item-trend.fxml
     */
    @FXML
    private StackedAreaChart<LocalDateTime, BigDecimal> chart;

    /**
     * FXML control injected from item-trend.fxml
     */
    @FXML
    private DatePicker dtpSince;

    /**
     * FXML control injected from item-trend.fxml
     */
    @FXML
    private DatePicker dtpUntil;

    /**
     * FXML control injected from item-trend.fxml
     */
    @FXML
    private Stage top;

    /**
     * FXML control injected from item-trend.fxml
     */
    @FXML
    private SearchableTextField<Item, FxItemMV> txtItem;

    @Override
    @FXML
    void onRefreshDBAction() {
        chart.getData().clear();
        dtpSince.commitValue();
        var since = dtpSince.getValue();
        dtpUntil.commitValue();
        var until = dtpUntil.getValue();
        if (since == null || until == null) return;

        var flow = new FxItemTrendFlow().withItem(txtItem.getValue());

        //If until is before since, invert order.
        if (until.isBefore(since)) {
            flow.withUntil(since).withSince(until);
            axisDate.setLowerBound(until.atStartOfDay());
            axisDate.setUpperBound(since.plusDays(2)
                    .atStartOfDay());
        }//Else, preserve order.
        else {
            flow.withSince(since).withUntil(until);
            axisDate.setLowerBound(since.atStartOfDay());
            axisDate.setUpperBound(until.plusDays(2)
                    .atStartOfDay());
        }

        flow.go(chart::setData);
    }

    @Override
    protected @NotNull Stage getTop() {
        return top;
    }

    @Override
    void onEditRowAction() {
        //DO NoTHING.
    }
}
