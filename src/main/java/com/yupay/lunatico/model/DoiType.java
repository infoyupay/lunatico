package com.yupay.lunatico.model;

/**
 * This represents the id document types.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public enum DoiType {
    /**
     * Id card number (DNI).
     */
    ID_CARD {
        @Override
        public String toString() {
            return "DNI";
        }
    },
    /**
     * Tax ID number.
     */
    TAX_ID {
        @Override
        public String toString() {
            return "RUC";
        }
    },
    /**
     * Other types of DOI.
     */
    OTHERS {
        @Override
        public String toString() {
            return "OTRO";
        }
    }
}
