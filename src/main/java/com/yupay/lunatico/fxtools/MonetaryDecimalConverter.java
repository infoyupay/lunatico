/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

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
