package com.yupay.lunatico.fxtools;

import javafx.beans.property.BooleanProperty;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * Static factory for common used functional implementations.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class Functionals {
    /**
     * Always fail constructor.
     *
     * @throws IllegalAccessException always.
     */
    @Contract("->fail")
    private Functionals() throws IllegalAccessException {
        throw new IllegalAccessException("Never instanciate a static factory.");
    }

    /**
     * Creates a consumer that sets the value of the
     * consumed property to the given value.
     * Shorthand for {@snippet : property -> property.set(value)}
     *
     * @param value the given value to set.
     * @return a new consumer, never null.
     */
    @Contract(pure = true, value = "_->new")
    public static @NotNull Consumer<BooleanProperty> setValue(boolean value) {
        return p -> p.set(value);
    }
}
