package com.yupay.lunatico.model;

/**
 * Types of virtual warehouse.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public enum VirtualWarehouseType {
    /**
     * In-transit items.
     */
    TRANSIT {
        @Override
        public String toString() {
            return "En Tránsito";
        }
    }
}
