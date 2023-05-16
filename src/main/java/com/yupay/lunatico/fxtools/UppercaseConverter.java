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
}
