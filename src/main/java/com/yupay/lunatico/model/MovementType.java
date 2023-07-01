package com.yupay.lunatico.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.stream.Stream;

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
        public boolean shouldSubtract() {
            return false;
        }

        @Override
        public String toString() {
            return "Entrada: Compras";
        }

        @Override
        public boolean shouldModifyPrice() {
            return true;
        }
    },
    /**
     * Input from customer return.
     */
    IN_RETURN {
        @Override
        public boolean shouldSubtract() {
            return false;
        }

        @Override
        public String toString() {
            return "Entrada: Devolución del Cliente";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Input from transfer/transit.
     */
    IN_TRANSFER {
        @Override
        public boolean shouldSubtract() {
            return false;
        }

        @Override
        public String toString() {
            return "Entrada: Transferencia";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Input from production.
     */
    IN_PRODUCTION {
        @Override
        public boolean shouldSubtract() {
            return false;
        }

        @Override
        public String toString() {
            return "Entrada: De Producción";
        }

        @Override
        public boolean shouldModifyPrice() {
            return true;
        }
    },
    /**
     * Input from fixings/adjustments.
     */
    IN_FIX {
        @Override
        public boolean shouldSubtract() {
            return false;
        }

        @Override
        public String toString() {
            return "Entrada: Ajuste";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Output to sales.
     */
    OUT_SALE {
        @Override
        public boolean shouldSubtract() {
            return true;
        }

        @Override
        public String toString() {
            return "Salida: Venta";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Output for returns to supplier.
     */
    OUT_RETURN {
        @Override
        public boolean shouldSubtract() {
            return true;
        }

        @Override
        public String toString() {
            return "Salida: Devolución al Proveedor";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Output for transfer/transit.
     */
    OUT_TRANSFER {
        @Override
        public boolean shouldSubtract() {
            return true;
        }

        @Override
        public String toString() {
            return "Salida: Transferencia";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Output for production.
     */
    OUT_PRODUCTION {
        @Override
        public boolean shouldSubtract() {
            return true;
        }

        @Override
        public String toString() {
            return "Salida: A Producción";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Output for fixing/adjustment.
     */
    OUT_FIX {
        @Override
        public boolean shouldSubtract() {
            return true;
        }

        @Override
        public String toString() {
            return "Salida: Ajuste";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Outputs due waste.
     */
    OUT_WASTE {
        @Override
        public boolean shouldSubtract() {
            return true;
        }

        @Override
        public String toString() {
            return "Salida: Merma";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    },
    /**
     * Outputs for free.
     */
    OUT_GIFT {
        @Override
        public boolean shouldSubtract() {
            return true;
        }

        @Override
        public String toString() {
            return "Salida: Regalo/Donación";
        }

        @Override
        public boolean shouldModifyPrice() {
            return false;
        }
    };

    /**
     * Creates a stream ponting to all values from this enumeration.
     * This is a shorthand for
     * {@snippet : Arrays.stream(MovementType.values());}
     *
     * @return an always new, never null stream.
     */
    @Contract("->new")
    public static @NotNull Stream<MovementType> stream() {
        return Arrays.stream(values());
    }

    /**
     * Flag to detect if the movement of this type
     * should substract the quantity balances.
     *
     * @return true or false depending on movement.
     */
    public abstract boolean shouldSubtract();

    /**
     * Flag to detect if the movement of this type
     * should modify (by computation) the unit price.
     *
     * @return true if the floating average method should be invoked.
     */
    public abstract boolean shouldModifyPrice();
}
