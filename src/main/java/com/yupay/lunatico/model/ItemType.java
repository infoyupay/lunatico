package com.yupay.lunatico.model;

/**
 * Types of item.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public enum ItemType {
    /**
     * This is an ingredient.
     */
    INGREDIENT {
        @Override
        public String toString() {
            return "Ingrediente";
        }
    },
    /**
     * This is a supply to be consumed, like oil,
     * salt, pepper, etc.
     */
    SUPPLY {
        @Override
        public String toString() {
            return "Consumibles";
        }
    },
    /**
     * This is a producto to be repacked. For instance,
     * a cake mould which must be cut and packed.
     */
    BYPRODUCT {
        @Override
        public String toString() {
            return "Producto Intermedio";
        }
    },
    /**
     * This is the end product, for instance a bite of cake.
     */
    PRODUCT {
        @Override
        public String toString() {
            return "Productos y Mercaderías";
        }
    },
}
