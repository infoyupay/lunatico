package com.yupay.lunatico.model;

/**
 * Type of balance being stored.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public enum BalanceType {
    /**
     * Snapshot balance, usually taken at the end of a given day.
     * As a feature of this system, nevertheless the snapshot may
     * be taken at any time, even more than once a day.
     */
    SNAPSHOT,
    /**
     * Historical balance used to accumulate all totals.
     */
    HISTORY
}
