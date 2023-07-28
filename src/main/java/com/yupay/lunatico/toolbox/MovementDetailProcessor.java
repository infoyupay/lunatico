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

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

/**
 * Consumer which takes a line of detail entry and updates all quantities and costs.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class MovementDetailProcessor
        implements Consumer<MovementDetailEntry> {


    @Override
    public void accept(@NotNull MovementDetailEntry x) {
        fillBefore(x);
        fillInput(x);
        fillOutput(x);
        fillBalance(x);
        //Now, local balances should be updated
        updateLocalBalance(x);
        updateGlobalBalance(x);
        updateHistoricalBalance(x);
    }

    /**
     * Fills the before balance of the line.
     *
     * @param x the movement detail entry object.
     */
    private void fillBefore(@NotNull MovementDetailEntry x) {
        var dt = x.detail();
        var localBalance = x.balance();
        dt.setBeforeQuantity(localBalance.getBalanceUnits());
        //Check if there's a previous computed unit price.
        if (localBalance.getBalanceUnitCost().compareTo(ZERO) == 0) {
            //If there's no a value, take from item global unit cost.
            dt.setBalancePrice(x.item().getBalanceUnitCost());
        } else {
            //Otherwise, take before price from local balance unit cost.
            dt.setBeforePrice(localBalance.getBalanceUnitCost());
        }
        //Take before total cost from lacalBalance.
        dt.setBeforeCost(localBalance.getBalanceCost());
    }

    /**
     * Fills the input part of the line.
     *
     * @param x the movement detail entry object.
     */
    private void fillInput(@NotNull MovementDetailEntry x) {
        var dt = x.detail();
        var typ = dt.getMovement().getType();
        //If shouldn't subtract, fill in.
        if (!typ.shouldSubtract()) {
            //In quantity
            dt.setInQuantity(dt.getQuantity());
            //If should modify unit price, take price from priceRef.
            if (typ.shouldModifyPrice()) {
                dt.setInPrice(dt.getPriceRef());
            }//Else, overwrite unit price.
            else{
                dt.setInPrice(dt.getBeforePrice());
                dt.setPriceRef(null);
            }
            //Compute IN cost
            dt.setInCost(dt.getInQuantity()
                    .multiply(dt.getInPrice())
                    .setScale(8, HALF_UP));
        }
    }

    /**
     * Fills the output part of the line.
     *
     * @param x the movement detail entry object.
     */
    private void fillOutput(@NotNull MovementDetailEntry x) {
        var dt = x.detail();
        //If should subtract, fill out.
        if (dt.getMovement().getType().shouldSubtract()) {
            //Out quantity
            dt.setOutQuantity(dt.getQuantity());
            //Out unit price
            dt.setOutPrice(dt.getBeforePrice());
            //Compute OUT cost
            dt.setOutCost(dt.getOutQuantity()
                    .multiply(dt.getOutPrice())
                    .setScale(8, HALF_UP));
        }
    }

    /**
     * Fills the end balance part of the line.
     *
     * @param x the movement detail entry.
     */
    private void fillBalance(@NotNull MovementDetailEntry x) {
        var dt = x.detail();
        //Balance quantity =
        dt.setBalanceQuantity(
                //Before quantity
                dt.getBeforeQuantity()
                        //+ input quantity
                        .add(dt.getInQuantity())
                        //- output quantity
                        .subtract(dt.getOutQuantity()));
        //The balance unit price depends of the modify price flag.
        //If unit price shall be modified
        if (dt.getMovement().getType().shouldModifyPrice()) {
            //Compute new total cost=
            dt.setBalanceCost(
                    //before cost
                    dt.getBeforeCost()
                            //+ input cost
                            .add(dt.getInCost())
                            //- output cost
                            .subtract(dt.getOutCost()));
            //Compute final price=
            dt.setBalancePrice(
                    //balance cost /
                    dt.getBalanceCost()
                            // balance quantity
                            .divide(dt.getBalanceQuantity(),
                                    //rounded up to 8 fractional digits.
                                    8, HALF_UP));
        }
        //Otherwise, if unit price won't be modified:
        else {
            //Take unit price/cost from movement line.
            dt.setBalancePrice(dt.getBeforePrice());
            //Compute current balance cost=
            dt.setBalanceCost(
                    //Balance quantity
                    dt.getBalanceQuantity()
                            //* balance price
                            .multiply(dt.getBalancePrice())
                            //Rounded up to 8 fractional digits
                            .setScale(8, HALF_UP));
        }
    }

    /**
     * Updates the local balance values.
     *
     * @param x entry to update.
     */
    private void updateLocalBalance(@NotNull MovementDetailEntry x) {
        x.balance().setBalanceUnits(x.detail().getBalanceQuantity());
        x.balance().setBalanceUnitCost(x.detail().getBalancePrice());
        x.balance().setBalanceCost(x.detail().getBalanceCost());
    }

    /**
     * Updates the global balance values.
     *
     * @param x entry to update.
     */
    private void updateGlobalBalance(@NotNull MovementDetailEntry x) {
        var item = x.item();
        var detail = x.detail();
        //Add and subtract balance units (quantity).
        item.setBalanceUnits(item.getBalanceUnits()
                .add(detail.getInQuantity())
                .subtract(detail.getOutQuantity()));
        //If it should modify pricing
        if (x.detail().getMovement().getType().shouldModifyPrice()) {
            //Compute new total cost by adding or subtracting
            item.setBalanceCost(item.getBalanceCost()
                    .add(detail.getInCost())
                    .subtract(detail.getOutCost()));
            //Compute new unit price by dividng
            item.setBalanceUnitCost(item.getBalanceCost()
                    .divide(item.getBalanceUnits(), 8, HALF_UP));
        }
        //Otherwise
        else {
            //Just multiply units * price
            item.setBalanceCost(item.getBalanceUnits()
                    .multiply(item.getBalanceUnitCost())
                    .setScale(8, HALF_UP));
        }
    }

    /**
     * Updates the historical balance amounts.
     *
     * @param x the entry to update.
     */
    private void updateHistoricalBalance(@NotNull MovementDetailEntry x) {
        //Extract the type.
        var typ = x.detail().getMovement().getType();
        x.balance()
                //Find in the details
                .getDetail()
                .stream()
                //For the detail with type from movement.
                .filter(b -> b.getSummaryType() == typ)
                .findAny()
                //When you find it
                .ifPresent(b -> {
                    //Update quantity to add the in or out quantity.
                    b.setQuantity(b.getQuantity()
                            .add(x.detail().getInQuantity())
                            .add(x.detail().getOutQuantity()));
                    //Update cost to add the in or out cost.
                    b.setCost(b.getCost()
                            .add(x.detail().getInCost())
                            .add(x.detail().getOutCost()));
                });
    }
}
