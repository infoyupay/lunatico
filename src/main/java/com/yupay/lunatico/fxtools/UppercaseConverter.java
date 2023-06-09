package com.yupay.lunatico.fxtools;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A string converter implementation to convert strings to their uppercase value.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class UppercaseConverter extends StringConverter<String> {

    /**
     * Maximum length. If 0, the output won't be limited.
     */
    private final int maxLen;

    /**
     * Full constructor.
     *
     * @param maxLen the maximum length of the output.
     *               If 0 or less, no limit will be set.
     */
    public UppercaseConverter(int maxLen) {
        this.maxLen = maxLen;
    }

    /**
     * Static factory to create a formatter using this implementation.
     * The result will set a propper change filtering.
     *
     * @param maxLen the maximum output length.
     *               If 0 or less, no limit will be set.
     * @return the text formatter.
     */
    @Contract("_ -> new")
    public static @NotNull TextFormatter<String> formatter(int maxLen) {
        return new TextFormatter<>(
                new UppercaseConverter(maxLen),
                "",
                c -> {
                    if (maxLen > 0
                            && c.getControlNewText()
                            .strip()
                            .length() > maxLen) {
                        return null;
                    }
                    c.setText(c.getText().toUpperCase());
                    return c;
                });
    }

    /**
     * A convenient method to process the text to an upper case one.
     *
     * @param text the text to convert to an upper case.
     * @return the upper cased text.
     */
    @Contract("null->null")
    private @Nullable String process(@Nullable String text) {
        if (text == null || text.isBlank()) return null;
        var str = text.strip().toUpperCase();
        if (maxLen > 0) {
            return str.length() > maxLen
                    ? str.substring(0, maxLen)
                    : str;
        } else {
            return str;
        }
    }

    @Override
    public String toString(String object) {
        return process(object);
    }

    @Override
    public String fromString(String string) {
        return process(string);
    }

    /**
     * Text formatter specialization for folio series and number,
     * which is backed up by an uppercase converter with a
     * maximum length of 20 characters.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    public static class FolioTextFormatter extends TextFormatter<String> {
        /**
         * Default constructor which creates an
         * UppercaseConverter with maxLen 20 as
         * a converter.
         */
        public FolioTextFormatter() {
            super(
                    new UppercaseConverter(20),
                    "",
                    c -> {
                        if (c.getControlNewText()
                                .strip()
                                .length() > 20) {
                            return null;
                        }
                        c.setText(c.getText().toUpperCase());
                        return c;
                    });
        }
    }

    /**
     * Text formatter specialization for notes and remarks
     * which is backed up by an uppercase converter with no
     * maximum characters length validation.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    public static class NotesTextFormatter extends TextFormatter<String> {
        /**
         * Default constructor that creates an Uppercase converter
         * with no characters limit.
         */
        public NotesTextFormatter() {
            super(
                    new UppercaseConverter(-1),
                    "",
                    c -> {
                        c.setText(c.getText().toUpperCase());
                        return c;
                    });
        }
    }
}
