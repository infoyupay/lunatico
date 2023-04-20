package com.yupay.lunatico;

import com.yupay.lunatico.fxmview.FxItem;
import com.yupay.lunatico.fxmview.FxStore;
import com.yupay.lunatico.fxmview.FxUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: REMOVE ON REAL IMPLEMENTATION.
 * <strong>THIS IS A PROTOTYPE FACTORY. DON'T USE IN PRODUCTION.</strong>
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class Prototypes {
    public static final ObservableList<FxStore> STORES = FXCollections.observableList(stores());
    public static final ObservableList<FxUnit> ALL_UNITS =
            FXCollections.observableList(List.of(units(), kilogram(), bottle(), liter(), bag()));

    private static List<FxStore> stores() {
        var r = new ArrayList<FxStore>();

        var r1 = new FxStore();
        r1.setId(0);
        r1.setName("Mamaquilla - Pacha");
        r1.setActive(true);
        r.add(r1);

        var r2 = new FxStore();
        r2.setId(1);
        r2.setName("Mamaquilla - Pacha (Bowls)");
        r2.setActive(true);
        r.add(r2);

        var r3 = new FxStore();
        r3.setId(2);
        r3.setName("CPSR - San Roque");
        r3.setActive(true);
        r.add(r3);

        return r;
    }

    public static List<FxItem> items() {
        var r = new ArrayList<FxItem>();

        var r1 = new FxItem();
        r1.setId(1);
        r1.setDescription("Arroz");
        r1.setUnit(kilogram());
        r1.setBalance(new BigDecimal("10.000"));
        r1.setBalanceStore(new BigDecimal("5.000"));
        r1.setBalanceTransit(new BigDecimal("5.000"));
        r.add(r1);

        var r2 = new FxItem();
        r2.setId(2);
        r2.setDescription("Aceite");
        r2.setUnit(liter());
        r2.setBalance(new BigDecimal("100.000"));
        r2.setBalanceStore(new BigDecimal("80.000"));
        r2.setBalanceSaved(new BigDecimal("20.000"));
        r.add(r2);

        return r;
    }

    public static FxUnit kilogram() {
        var r = new FxUnit();
        r.setId(1);
        r.setName("Kilos");
        r.setSymbol("Kg");
        return r;
    }

    public static FxUnit bag() {
        var r = new FxUnit();
        r.setId(2);
        r.setName("Sacos");
        r.setSymbol("Bg");
        return r;
    }

    public static FxUnit liter() {
        var r = new FxUnit();
        r.setId(3);
        r.setName("litros");
        r.setSymbol("Lt");
        return r;
    }

    public static FxUnit bottle() {
        var r = new FxUnit();
        r.setId(4);
        r.setName("Botella");
        r.setSymbol("Bt");
        return r;
    }

    public static FxUnit units() {
        var r = new FxUnit();
        r.setId(5);
        r.setName("Unidad");
        r.setSymbol("Und");
        return r;
    }
}
