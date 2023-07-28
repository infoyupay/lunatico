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

import com.yupay.lunatico.fxforms.ColumnBuilder;
import com.yupay.lunatico.fxforms.FxForms;
import com.yupay.lunatico.fxforms.FxSearchCard;
import com.yupay.lunatico.fxmview.FxItemMV;
import com.yupay.lunatico.fxmview.FxPersonMV;
import com.yupay.lunatico.fxmview.FxWarehouseMV;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Static factory for {@link FxSearchCard} instances.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxSearchCards {
    /**
     * Creates a new search card to choose Warehouse items.
     *
     * @param items the elements to choose from.
     * @return a new search card.
     */
    @Contract("_->new")
    public static @NotNull FxSearchCard<FxItemMV> forItem(@NotNull List<FxItemMV> items) {
        return FxForms.<FxItemMV>searchCard()
                .addColumn(
                        ColumnBuilder.forLong(FxItemMV::idProperty)
                                .withTitle("Código")
                                .withPrefWidth(75)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxItemMV::nameProperty)
                                .withTitle("Descripción")
                                .withPrefWidth(250)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxItemMV::typeProperty)
                                .withTitle("Tipo")
                                .withPrefWidth(150)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxItemMV::unitProperty)
                                .withTitle("U. Medida")
                                .withPrefWidth(150)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxItemMV::notesProperty)
                                .withTitle("Notas")
                                .withPrefWidth(250)
                                .get())
                .setupFilter((p, i) -> p.matcher(Long.toString(i.getId())).matches()
                        || p.matcher(i.getName()).matches())
                .setupData(items);
    }

    /**
     * Creates a new search card to choose Persons.
     *
     * @param items the elements to choose from.
     * @return a new search card.
     */
    @Contract("_->new")
    public static @NotNull FxSearchCard<FxPersonMV> forPerson(@NotNull List<FxPersonMV> items) {
        return FxForms.<FxPersonMV>searchCard()
                .addColumn(
                        ColumnBuilder.forLong(FxPersonMV::idProperty)
                                .withTitle("Código")
                                .withPrefWidth(75)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxPersonMV::doiTypeProperty)
                                .withTitle("DOI")
                                .withPrefWidth(100)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxPersonMV::doiNumProperty)
                                .withTitle("Número de DOI")
                                .withPrefWidth(150)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxPersonMV::nameProperty)
                                .withTitle("Nombre")
                                .withPrefWidth(350)
                                .get())
                .setupFilter((p, i) -> {
                    var r = p.matcher(Long.toString(i.getId())).matches();
                    r |= p.matcher(i.getName()).matches();
                    if (i.getDoiNum() != null && !i.getDoiNum().isBlank()) {
                        r |= p.matcher(i.getDoiNum()).matches();
                    }
                    return r;
                })
                .setupData(items);
    }

    /**
     * Creates a new search card to choose Warehouses.
     *
     * @param items the elements to choose from.
     * @return a new search card.
     */
    @Contract("_->new")
    public static @NotNull FxSearchCard<FxWarehouseMV> forWarehouse(@NotNull List<FxWarehouseMV> items) {
        return FxForms.<FxWarehouseMV>searchCard()
                .addColumn(
                        ColumnBuilder.forLong(FxWarehouseMV::idProperty)
                                .withTitle("Código")
                                .withPrefWidth(75)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxWarehouseMV::nameProperty)
                                .withTitle("Nombre")
                                .withPrefWidth(300)
                                .get())
                .addColumn(
                        ColumnBuilder.forObject(FxWarehouseMV::virtualTypeProperty)
                                .withTitle("Virtual")
                                .withPrefWidth(150)
                                .get())
                .setupFilter((p, i) -> p.matcher(i.getName()).matches())
                .setupData(items);
    }
}
