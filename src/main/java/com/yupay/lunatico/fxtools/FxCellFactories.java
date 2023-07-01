package com.yupay.lunatico.fxtools;

import com.yupay.lunatico.fxmview.FxItemMV;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

public class FxCellFactories {
    /**
     * Creates a cell factory to show the ID value of an Item model-view entity.
     *
     * @param <S> type erasure of table view model.
     * @return the new callback to use as factory.
     */
    @Contract("->new")
    public static <S> @NotNull Callback<TableColumn<S, FxItemMV>, TableCell<S, FxItemMV>>
    itemShowingId() {
        return TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(FxItemMV object) {
                return object == null ? "" : Long.toString(object.getId());
            }

            @Override
            public FxItemMV fromString(String string) {
                return null;
            }
        });
    }

    /**
     * Creates a cell factory to show the name value of an Item model-view entity.
     *
     * @param <S> type erasure of table view model.
     * @return the new callback to use as factory.
     */
    @Contract("->new")
    public static <S> @NotNull Callback<TableColumn<S, FxItemMV>, TableCell<S, FxItemMV>>
    itemShowingName() {
        return TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(FxItemMV object) {
                return object == null ? "" : object.getName();
            }

            @Override
            public FxItemMV fromString(String string) {
                return null;
            }
        });
    }

    /**
     * Creates a cell factory to show the unit value of an Item model-view entity.
     *
     * @param <S> type erasure of table view model.
     * @return the new callback to use as factory.
     */
    @Contract("->new")
    public static <S> @NotNull Callback<TableColumn<S, FxItemMV>, TableCell<S, FxItemMV>>
    itemShowingUnit() {
        return TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(FxItemMV object) {
                return object == null ? "" : Objects.toString(object.getUnit(), "");
            }

            @Override
            public FxItemMV fromString(String string) {
                return null;
            }
        });
    }

    /**
     * Creates a cell factory to show big decimal values with 8 fractional digits.
     *
     * @param <S> type erasure of table view model.
     * @return the new callback to use as factory.
     */
    @Contract("->new")
    public static <S> @NotNull Callback<TableColumn<S, BigDecimal>, TableCell<S, BigDecimal>>
    decimalQuantity() {
        return TextFieldTableCell.forTableColumn(new BigDecimalConverter(8, false));
    }
}
