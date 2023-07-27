package com.yupay.lunatico.fxtools;

/**
 * Decimal converter implementation to use a currency symbol.
 * The currency symbol is S/
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class MonetaryDecimalConverter extends BaseDecimalConverter {
    /**
     * Constructor that uses mask with
     * a currency symbol and 8 fractional digits.
     *
     * @param scale      the required scale for converted values.
     * @param abs        the absolute value flag.
     */
    public MonetaryDecimalConverter(int scale, boolean abs) {
        super("¤ #,##0.00000000;#,##0.#",
                scale, abs);
    }
}
