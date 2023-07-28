/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

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
