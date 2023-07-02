package com.yupay.lunatico.fxtools;

import javafx.util.StringConverter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import static java.math.RoundingMode.HALF_UP;

public class BigDecimalConverter extends StringConverter<BigDecimal> {
    private final DecimalFormat format;
    private final int scale;

    private final boolean abs;

    public BigDecimalConverter(int scale, boolean abs) {
        format = new DecimalFormat(
                "#,##0.00000000;#,##0",
                PeruvianLocalization.SYM_PERU);
        format.setParseBigDecimal(true);
        format.setRoundingMode(HALF_UP);
        this.scale = scale;
        this.abs = abs;
    }

    @Contract(" -> new")
    public static @NotNull BigDecimalConverter forKardex() {
        return new BigDecimalConverter(8, true);
    }

    @Override
    public String toString(BigDecimal object) {
        return object == null ? "" : format.format(object);
    }

    @Override
    public BigDecimal fromString(String string) {
        if (string == null || string.isBlank()) {
            return null;
        } else {
            try {
                var parsed = format.parse(string.strip());
                BigDecimal r;
                if (parsed instanceof BigDecimal bd) {
                    r = bd;
                } else if (parsed == null) {
                    return null;
                } else {
                    r = BigDecimal.valueOf(parsed.doubleValue())
                            .setScale(scale, HALF_UP);
                }
                if (abs) r = r.abs();
                return r;
            } catch (ParseException e) {
                return null;
            }
        }
    }
}
