package com.yupay.lunatico.fxtools;

import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

import static java.math.RoundingMode.HALF_UP;

/**
 * Base class for decimal converters.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class BaseDecimalConverter extends StringConverter<BigDecimal> {
    /**
     * Required scale for converted values,
     * all display values will have the scale
     * specified by format.
     */
    private final int scale;
    /**
     * Absolute value flag, if true all results
     * will be positive.
     */
    private final boolean abs;
    /**
     * The formatter.
     */
    @NotNull
    protected DecimalFormat format;

    /**
     * Constructor that builds the formatter from given mask.
     *
     * @param formatMask the mask to use in the formatter.
     * @param scale      the required scale for converted values.
     * @param abs        the absolute value flag.
     */
    protected BaseDecimalConverter(
            @NotNull String formatMask,
            int scale,
            boolean abs) {
        this.format = new DecimalFormat(
                formatMask,
                PeruvianLocalization.SYM_PERU);
        this.scale = scale;
        this.abs = abs;
    }

    @Override
    public final @NotNull String toString(BigDecimal object) {
        return object == null ? "" : format.format(object);
    }

    @Override
    public final BigDecimal fromString(String string) {
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
