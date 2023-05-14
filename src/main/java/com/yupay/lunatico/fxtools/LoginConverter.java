package com.yupay.lunatico.fxtools;

import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * Text formatter suitable for login names.
 * It'll prevent any spaces and will enforce
 * lowercasing. It won't limit login name size.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class LoginConverter extends StringConverter<String>
        implements UnaryOperator<TextFormatter.Change> {
    /**
     * Static factory of a text formatter based upon this implementation.
     *
     * @return a new text formatter.
     */
    @Contract("->new")
    public static @NotNull TextFormatter<String> formatter() {
        var r = new LoginConverter();
        return new TextFormatter<>(r, "", r);
    }

    @Override
    public TextFormatter.Change apply(TextFormatter.@NotNull Change c) {
        if (c.getControlNewText().codePoints()
                .anyMatch(Character::isSpaceChar)) {
            return null;
        } else {
            c.setText(c.getText().toLowerCase());
            return c;
        }
    }

    @Override
    public String toString(String object) {
        return Objects.toString(object, "").toLowerCase();
    }

    @Override
    public String fromString(String string) {
        if (string == null) return "";
        else return
                string.codePoints()
                        .filter(i -> !Character.isSpaceChar(i))
                        .mapToObj(Character::toString)
                        .collect(Collectors.joining())
                        .toLowerCase();
    }
}
