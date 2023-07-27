package com.yupay.lunatico.fxtools;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.math.RoundingMode.HALF_UP;

/**
 * A special big decimal converter to use the formatter #,##0.00000000
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class BigDecimalConverter extends BaseDecimalConverter {

    /**
     * Constructor that creates the mask #,##0.00000000.
     *
     * @param scale the required scale for converted values.
     * @param abs   the absolute value flag.
     */
    public BigDecimalConverter(int scale, boolean abs) {
        super("#,##0.00000000;#,##0", scale, abs);
        format.setParseBigDecimal(true);
        format.setRoundingMode(HALF_UP);
    }

    /**
     * Convenient converter to use for kardex units.
     * The converter will have a flag of absolute value
     * true, and scale 8.
     *
     * @return a new converter, never null.
     */
    @Contract(" -> new")
    public static @NotNull BigDecimalConverter forKardex() {
        return new BigDecimalConverter(8, true);
    }

}
