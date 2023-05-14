package com.yupay.lunatico.fxforms;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.util.function.Consumer;

/**
 * A default error handler that shows a stack trace of the exception,
 * all in one alert dialog box.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class ErrorHandler extends Alert implements Consumer<Throwable> {
    /**
     * The text area for the stack trace.
     */
    private final TextArea stack = new TextArea();

    /**
     * Default constructor that initializes all components.
     */
    public ErrorHandler() {
        super(AlertType.ERROR,
                """
                        Ha ocurrido un error con esta operación.
                        Antes de contactar a sistemas, revise sus datos y vuelva a intentar.
                        Para más información, el expandible contiene un volcado de pila.""",
                ButtonType.CLOSE);
        initialize();
    }

    /**
     * Initializes the stack text area.
     */
    private void initialize() {
        stack.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        stack.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        stack.setPrefSize(600.0, 200.0);
        stack.setEditable(false);
        getDialogPane().setExpandableContent(stack);
        getDialogPane().setMinWidth(610.0);
    }

    /**
     * Fluent setter - with.
     *
     * @param briefing the brief text about the error.
     * @return this instance.
     */
    @Contract("_->this")
    public @NotNull ErrorHandler withBriefing(@NotNull String briefing) {
        setHeaderText(briefing);
        return this;
    }

    @Override
    public void accept(@NotNull Throwable throwable) {
        try (var sw = new StringWriter();
             var pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            stack.setText(sw.toString());
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to write stack trace.", e);
        } finally {
            showAndWait();
        }
    }
}
