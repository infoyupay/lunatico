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
