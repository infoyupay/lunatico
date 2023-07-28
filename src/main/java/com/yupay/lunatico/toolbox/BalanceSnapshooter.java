/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.toolbox;

import com.yupay.lunatico.fxforms.FxLunatico;
import com.yupay.lunatico.model.Balance;
import com.yupay.lunatico.model.BalanceDetail;
import com.yupay.lunatico.model.BalanceType;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.UnaryOperator;

/**
 * Function (unary operator) to create a new Balance snapshot from the
 * existing historic balance in the database.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class BalanceSnapshooter implements UnaryOperator<Balance> {
    /**
     * Default constructor.
     */
    public BalanceSnapshooter() {
    }

    @Override
    @Contract("_->new")
    public @NotNull Balance apply(@NotNull Balance balance) {
        var r = new Balance();
        r.setBalanceCost(balance.getBalanceCost());
        r.setBalanceUnitCost(balance.getBalanceUnitCost());
        r.setBalanceUnits(balance.getBalanceUnits());
        r.setItem(balance.getItem());
        r.setType(BalanceType.SNAPSHOT);
        r.setUser(FxLunatico.APP_CONTROLLER.getLoggedUser().toModel());
        r.setWarehouse(balance.getWarehouse());
        r.setDetail(balance
                .getDetail()
                .parallelStream()
                .map(this::applyToDetail)
                .peek(x -> x.setBalance(r))
                .toList());
        return r;
    }

    /**
     * Creates the snapshot detail from the historic detail.
     *
     * @param detail the historic detail element.
     * @return a new snapshot detail.
     */
    @Contract("_->new")
    private @NotNull BalanceDetail applyToDetail(@NotNull BalanceDetail detail) {
        var r = new BalanceDetail();
        r.setCost(detail.getCost());
        r.setQuantity(detail.getQuantity());
        r.setSummaryType(detail.getSummaryType());
        return r;
    }
}
