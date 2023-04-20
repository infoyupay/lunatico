package com.yupay.lunatico.model;

/**
 * Type of balance being stored.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public enum BalanceType {
    /**
     * The opening balance.
     */
    OPENING,
    /**
     * Closure balance.
     */
    CLOSURE,
    /**
     * Helper balance used to accumulate current day totals.
     */
    HELPER
}
