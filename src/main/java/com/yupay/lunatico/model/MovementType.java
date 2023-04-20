package com.yupay.lunatico.model;

/**
 * Types of movement.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public enum MovementType {
    /**
     * Input from purchases.
     */
    IN_PURCHASE {
        @Override
        public String toString() {
            return "Entrada: Compras";
        }
    },
    /**
     * Input from customer return.
     */
    IN_RETURN {
        @Override
        public String toString() {
            return "Entrada: Devolución del Cliente";
        }
    },
    /**
     * Input from transfer/transit.
     */
    IN_TRANSFER {
        @Override
        public String toString() {
            return "Entrada: Transferencia";
        }
    },
    /**
     * Input from production.
     */
    IN_PRODUCTION {
        @Override
        public String toString() {
            return "Entrada: De Producción";
        }
    },
    /**
     * Input from fixings/adjustments.
     */
    IN_FIX {
        @Override
        public String toString() {
            return "Entrada: Ajuste";
        }
    },
    /**
     * Output to sales.
     */
    OUT_SALE {
        @Override
        public String toString() {
            return "Salida: Venta";
        }
    },
    /**
     * Output for returns to supplier.
     */
    OUT_RETURN {
        @Override
        public String toString() {
            return "Salida: Devolución al Proveedor";
        }
    },
    /**
     * Output for transfer/transit.
     */
    OUT_TRANSFER {
        @Override
        public String toString() {
            return "Salida: Transferencia";
        }
    },
    /**
     * Output for production.
     */
    OUT_PRODUCTION {
        @Override
        public String toString() {
            return "Salida: A Producción";
        }
    },
    /**
     * Output for fixing/adjustment.
     */
    OUT_FIX {
        @Override
        public String toString() {
            return "Salida: Ajuste";
        }
    },
    /**
     * Outputs due waste.
     */
    OUT_WASTE {
        @Override
        public String toString() {
            return "Salida: Merma";
        }
    },
    /**
     * Outputs for free.
     */
    OUT_GIFT {
        @Override
        public String toString() {
            return "Salida: Regalo/Donación";
        }
    }
}
