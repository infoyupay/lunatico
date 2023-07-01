package com.yupay.lunatico.fxtools;

import com.yupay.lunatico.model.ModelView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * This is an extension of a text field that shows
 * a text matching a formatter function and if user
 * sets a new text, the underlying value will be searched
 * from the database through a search flow.
 *
 * @param <T> type erasure of entity.
 * @param <U> type erasure of model view for T.
 * @author InfoYupay SACS
 * @version 1.0
 */
public class SearchableTextField<T, U extends ModelView<T, U>>
        extends TextField {
    /**
     * The current value.
     */
    private final ObjectProperty<U> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * Function to create the propper string for a given U value.
     * It must consider a null value as well.
     */
    private final ObjectProperty<Function<U, String>> stringMaker =
            new SimpleObjectProperty<>(this, "stringMaker");
    /**
     * Function to map a String into the entity through a search process.
     */
    private final ObjectProperty<Function<String, Optional<U>>> searchExecutor =
            new SimpleObjectProperty<>(this, "searchExecutor");
    /**
     * Fx card supplier.
     */
    private final ObjectProperty<Supplier<CardDialog<U>>> cardSupplier =
            new SimpleObjectProperty<>(this, "cardSupplier");

    /**
     * Creates a {@code TextField} with empty text content.
     */
    public SearchableTextField() {
        focusedProperty().addListener((x, o, n) -> {
            //If new value, gain focus.
            if (o) adjustText();
        });

        var mniCopy = new MenuItem("Copiar");
        mniCopy.setOnAction(e -> {
            if (e.isConsumed()) return;
            var cb = new ClipboardContent();
            cb.putString(getStringMaker().apply(getValue()));
            Clipboard.getSystemClipboard().setContent(cb);
            e.consume();
        });
        mniCopy.disableProperty().bind(value.isNull());

        var mniFind = new MenuItem("Buscar");
        mniFind.setOnAction(e -> {
            if (e.isConsumed()) return;
            commit();
            e.consume();
        });

        var mniView = new MenuItem("Ver detalle");
        mniView.setOnAction(e -> {
            if (e.isConsumed()) return;
            getCardSupplier().get().viewer(null, getValue());
            e.consume();
        });
        mniView.disableProperty().bind(valueProperty().isNull()
                .or(cardSupplierProperty().isNull()));

        setContextMenu(new ContextMenu(mniFind, new SeparatorMenuItem(), mniCopy, mniView));

        addEventHandler(ActionEvent.ACTION, e -> commit());
        addEventHandler(KeyEvent.KEY_RELEASED, this::keyReleased);
        valueProperty().addListener(o -> adjustText());
    }

    /**
     * Event handler for key event.
     *
     * @param evt the key event.
     */
    private void keyReleased(@NotNull KeyEvent evt) {
        if (!evt.isConsumed() && evt.getCode() == KeyCode.F3) {
            evt.consume();
            commit();
        }
    }

    /**
     * Commits the new text to the search executor
     * to retrieve a new value.
     */
    public void commit() {
        var txt = getText();
        if (txt == null || txt.isBlank()) {
            setValue(null);
        } else {
            Optional.ofNullable(getSearchExecutor())
                    .flatMap(f -> f.apply(txt.strip()))
                    .ifPresentOrElse(this::setValue, this::adjustText);
        }
    }

    /**
     * Adjusts the text from the underlying value.
     */
    public void adjustText() {
        if (getStringMaker() == null) {
            setText(Objects.toString(getValue(), ""));
        } else {
            setText(getStringMaker().apply(getValue()));
        }
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final U getValue() {
        return value.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param value value to assign into {@link #value}.
     */
    public final void setValue(U value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<U> valueProperty() {
        return value;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #stringMaker}.get();
     */
    public final Function<U, String> getStringMaker() {
        return stringMaker.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param stringMaker value to assign into {@link #stringMaker}.
     */
    public final void setStringMaker(Function<U, String> stringMaker) {
        this.stringMaker.set(stringMaker);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #stringMaker}.
     */
    @SuppressWarnings("unused")
    public final ObjectProperty<Function<U, String>> stringMakerProperty() {
        return stringMaker;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #searchExecutor}.get();
     */
    public final Function<String, Optional<U>> getSearchExecutor() {
        return searchExecutor.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param searchExecutor value to assign into {@link #searchExecutor}.
     */
    public final void setSearchExecutor(Function<String, Optional<U>> searchExecutor) {
        this.searchExecutor.set(searchExecutor);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #searchExecutor}.
     */
    @SuppressWarnings("unused")
    public final ObjectProperty<Function<String, Optional<U>>> searchExecutorProperty() {
        return searchExecutor;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #cardSupplier}.get();
     */
    public final Supplier<CardDialog<U>> getCardSupplier() {
        return cardSupplier.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param cardSupplier value to assign into {@link #cardSupplier}.
     */
    public final void setCardSupplier(Supplier<CardDialog<U>> cardSupplier) {
        this.cardSupplier.set(cardSupplier);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #cardSupplier}.
     */
    public final ObjectProperty<Supplier<CardDialog<U>>> cardSupplierProperty() {
        return cardSupplier;
    }

}
