package com.yupay.lunatico.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * The model view is an intermediary object between
 * the model layer and user experience / user interface.
 * This abstraction will help to make the ModelView
 * pattern work.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class ModelView<T, U extends ModelView<T, U>> {
    /**
     * Constructor to copy information from a model entity.
     *
     * @param model the model entity.
     */
    @SuppressWarnings("unused")
    public ModelView(@NotNull T model) {

    }

    /**
     * Default no-op constructor.
     */
    public ModelView() {
    }

    /**
     * Creates a deep copy of this.
     *
     * @return never null.
     */
    @Contract("->new")
    public abstract @NotNull U deepCopy();

    /**
     * Creates a model entity based on this' information.
     *
     * @return the model entity.
     */
    @Contract("->new")
    public abstract @NotNull T toModel();

    /**
     * Copies information from the model entity.
     *
     * @param model the "source" model entity.
     */
    public abstract void fromModel(@NotNull T model);
}
