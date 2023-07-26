package com.yupay.lunatico.fxtools;

import javafx.scene.chart.Axis;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Customization of JavaFX Chart axis, to create an horizontal
 * axis that shows LocalDateTime values, the ticks are formatted
 * as local dates.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxTimestampAxis extends Axis<LocalDateTime> {
    /**
     * The current time scale, used as range.
     */
    private TimeScale scale = new TimeScale(0D, 0D);
    /**
     * Lower date time bound.
     */
    private LocalDateTime lowerBound;
    /**
     * Upper date and time bound.
     */
    private LocalDateTime upperBound;

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #lowerBound}
     */
    public LocalDateTime getLowerBound() {
        return lowerBound;
    }

    /**
     * Public accessor - setter.
     *
     * @param lowerBound value to set into {@link #lowerBound}
     */
    public void setLowerBound(LocalDateTime lowerBound) {
        this.lowerBound = lowerBound;
        scale.setRealBounds(getLowerBound(), getUpperBound());
        //Setting the lower bound must invalidate range.
        invalidateRange();
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #upperBound}
     */
    public LocalDateTime getUpperBound() {
        return upperBound;
    }

    /**
     * Public accessor - setter.
     *
     * @param upperBound value to set into {@link #upperBound}
     */
    public void setUpperBound(LocalDateTime upperBound) {
        this.upperBound = upperBound;
        scale.setRealBounds(getLowerBound(), getUpperBound());
        //Writing the upper bound should invalidate range.
        invalidateRange();
    }

    @Override
    protected Object autoRange(double length) {
        return scale.withMapUnits(length);
    }

    @Override
    protected void setRange(Object range, boolean animate) {
        if (range instanceof TimeScale scaleInstance) {
            this.scale = scaleInstance;
        }
    }

    @Override
    protected Object getRange() {
        return scale;
    }

    @Override
    public double getZeroPosition() {
        return 0;
    }

    @Override
    public double getDisplayPosition(LocalDateTime value) {
        var numeric = toNumericValue(value) - toNumericValue(getLowerBound());
        return Double.isNaN(numeric) ? numeric : scale.toMapLength(numeric);
    }

    @Override
    public LocalDateTime getValueForDisplay(double displayPosition) {
        var epoch = scale.toRealLength(displayPosition) + toNumericValue(getLowerBound());
        return toRealValue(epoch);
    }

    @Override
    public boolean isValueOnAxis(LocalDateTime value) {
        if (lowerBound == null || upperBound == null) return false;
        else return !(value.isBefore(lowerBound) || value.isAfter(upperBound));
    }

    @Override
    public double toNumericValue(LocalDateTime value) {
        return value == null
                ? Double.NaN
                : value.toEpochSecond(PeruvianLocalization.SYS_OFFSET);
    }

    @Override
    public LocalDateTime toRealValue(double value) {
        //If value is 0 or negative, or if value is not finite
        if (value < 0 || !Double.isFinite(value)) {
            //Return null becaue value is not valid.
            return null;
        }//Else, use the help of BigDecimal
        else {
            //The big decimal representing the epoch value.
            var bigEpoch = BigDecimal.valueOf(value);
            //The number of seconds of eopch is integer part.
            var seconds = bigEpoch.setScale(0, RoundingMode.DOWN);
            //The nanos is the fractional part up to 9 digits.
            var nanos = bigEpoch.subtract(seconds)
                    .movePointRight(9)
                    .setScale(0, RoundingMode.DOWN)
                    .intValueExact();
            //Re-create the local date time object.
            return LocalDateTime.ofEpochSecond(
                    seconds.longValueExact(),
                    nanos,
                    PeruvianLocalization.SYS_OFFSET);
        }
    }

    @Override
    protected List<LocalDateTime> calculateTickValues(double length, Object range) {
        //If length (which is the graphical part) is less than 150, no ticks.
        if (length < 150
                || range == null
                || lowerBound == null
                || upperBound == null) {
            return List.of();
        }//If the range is a time scale object.
        else if (range instanceof TimeScale scaleInstance) {
            var r = new ArrayList<LocalDateTime>();
            //The first tick is the lower bound.
            r.add(getLowerBound());
            //Iterate:
            for (long i = (long) toNumericValue(getLowerBound()), //starting at lower bound.
                 step = (long) scaleInstance.toRealLength(75D);//Each step is of 75px.
                 i < (long) toNumericValue(getUpperBound()); //While it's less than upper bound.
                 i += step) {
                //add tick.
                r.add(toRealValue(i));
            }
            //Final tick, the upper bound.
            r.add(getUpperBound());
            return r;
        } else {
            return List.of();
        }
    }

    @Override
    protected String getTickMarkLabel(LocalDateTime value) {
        return value == null ? "" : SmartDateConverter.OUTPUT_MASK.format(value);
    }

    /**
     * The time scale is a fancy wrapper to have
     * a range in the axis API.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    public static final class TimeScale {
        /**
         * Range units in the map model.
         * This is obtained fro length parameter
         * in some methods of JavaFX axis.
         */
        private double mapUnits;
        /**
         * Real units represented by map units.
         * This is obtained from the difference
         * of epoch seconds between upper and lower bound.
         */
        private double realUnits;

        /**
         * Canonical constructor.
         *
         * @param mapUnits  the map units length.
         * @param realUnits the real units length.
         */
        public TimeScale(double mapUnits, double realUnits) {
            this.mapUnits = mapUnits;
            this.realUnits = realUnits;
        }

        /**
         * Using proportions and arithmetics will
         * compute the real length represented by an
         * amount of map length units.
         *
         * @param mapLength the amount of map length units.
         * @return the real length (in seconds of epoch)
         * represented by the given mapLength.
         */
        public double toRealLength(double mapLength) {
            return mapLength * realUnits / mapUnits;
        }

        /**
         * Using proportions and arithmetics will
         * compute the amount of display units (map length)
         * required to represent a given difference between
         * two seconds of epoch.
         *
         * @param realLength the difference between two seconds of epoch.
         * @return the amount of diplay units (map length) to represent
         * this elapsed time.
         */
        public double toMapLength(double realLength) {
            return mapUnits * realLength / realUnits;
        }

        /**
         * Public accessor - getter.
         *
         * @return value of {@link #mapUnits}
         */
        public double getMapUnits() {
            return mapUnits;
        }

        /**
         * Public accessor - setter.
         *
         * @param mapUnits value to set into {@link #mapUnits}
         */
        public void setMapUnits(double mapUnits) {
            this.mapUnits = mapUnits;
        }

        /**
         * Public accessor - getter.
         *
         * @return value of {@link #realUnits}
         */
        public double getRealUnits() {
            return realUnits;
        }

        /**
         * Public accessor - setter.
         *
         * @param realUnits value to set into {@link #realUnits}
         */
        public void setRealUnits(double realUnits) {
            this.realUnits = Math.abs(realUnits);
        }

        /**
         * Creates a copy of this object but
         * with given map length.
         *
         * @param length given map length.
         * @return a copy of this with above length.
         */
        @Contract(value = "_ -> new", pure = true)
        public @NotNull TimeScale withMapUnits(double length) {
            return new TimeScale(length, this.realUnits);
        }

        /**
         * Convenient method to set the real length from
         * local date time bounds.
         *
         * @param lowerBound lower bound local date time.
         * @param upperBound upper bound local date time.
         */
        public void setRealBounds(LocalDateTime lowerBound,
                                  LocalDateTime upperBound) {
            //If any bound is null, then no real length would be represented.
            if (lowerBound == null || upperBound == null) {
                setRealUnits(0D);
            } else {
                setRealUnits(
                        upperBound.toEpochSecond(PeruvianLocalization.SYS_OFFSET) -
                                lowerBound.toEpochSecond(PeruvianLocalization.SYS_OFFSET));
            }
        }
    }

}
