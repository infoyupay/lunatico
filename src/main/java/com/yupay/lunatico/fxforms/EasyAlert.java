/*
 *      This file is part of Lunatico project.
 *
 *     Lunatico is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 *     Foobar is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */

package com.yupay.lunatico.fxforms;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * An easy alert allows to create alerts in a chained fashion.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class EasyAlert extends Alert
        implements Runnable {
    /**
     * Creates an alert with the given AlertType (refer to the {@link AlertType}
     * documentation for clarification over which one is most appropriate).
     *
     * <p>By passing in an AlertType, default values for the
     * {@link #titleProperty() title}, {@link #headerTextProperty() headerText},
     * and {@link #graphicProperty() graphic} properties are set, as well as the
     * relevant {@link #getButtonTypes() buttons} being installed. Once the Alert
     * is instantiated, developers are able to modify the values of the alert as
     * desired.
     *
     * <p>It is important to note that the one property that does not have a
     * default value set, and which therefore the developer must set, is the
     * {@link #contentTextProperty() content text} property (or alternatively,
     * the developer may call {@code alert.getDialogPane().setContent(Node)} if
     * they want a more complex alert). If the contentText (or content) properties
     * are not set, there is no useful information presented to end users.
     *
     * @param alertType an alert with the given AlertType
     */
    private EasyAlert(AlertType alertType) {
        super(alertType);
        initModality(Modality.APPLICATION_MODAL);
    }

    /**
     * Creates a new alert for information.
     *
     * @return an information alert.
     */
    @Contract(" -> new")
    public static @NotNull EasyAlert info() {
        return new EasyAlert(AlertType.INFORMATION);
    }

    /**
     * Creates a new alert for warning.
     *
     * @return a warning alert.
     */
    @Contract(" -> new")
    public static @NotNull EasyAlert warning() {
        return new EasyAlert(AlertType.WARNING);
    }

    /**
     * Creates a new alert for information showing a
     * successful database update completed.
     *
     * @param content the content text to show.
     * @return an information alert.
     */
    @Contract(" _-> new")
    public static @NotNull EasyAlert editCompleted(@NotNull String content) {
        return info()
                .withTitle("Completado")
                .withHeaderText("Actualización completada satisfactoriamente.")
                .withContentText(content)
                .buttonOkOnly();
    }

    /**
     * Static factory to create a new alert for errors.
     *
     * @return an error alert.
     */
    @Contract(" -> new")
    public static @NotNull EasyAlert error() {
        return new EasyAlert(AlertType.ERROR);
    }

    /**
     * Fluent setter - with.
     *
     * @param title the new value.
     * @return this instance.
     * @see Alert#setTitle(String)
     */
    @Contract("_->this")
    public @NotNull EasyAlert withTitle(@NotNull String title) {
        setTitle(title);
        return this;
    }

    /**
     * Fluent setter - with.
     *
     * @param headerText the new value.
     * @return this instance.
     * @see Alert#setHeaderText(String)
     */
    @Contract("_->this")
    public @NotNull EasyAlert withHeaderText(@NotNull String headerText) {
        setHeaderText(headerText);
        return this;
    }

    /**
     * Fluent setter - with.
     *
     * @param contentText the new value.
     * @return this instance.
     * @see Alert#setContentText(String)
     */
    @Contract("_->this")
    public @NotNull EasyAlert withContentText(@NotNull String contentText) {
        setContentText(contentText);
        return this;
    }

    /**
     * Fluent setter - with.
     *
     * @param buttonTypes the new value.
     * @return this instance.
     * @see Alert#getButtonTypes()
     */
    @Contract("_->this")
    public @NotNull EasyAlert withButtonTypes(@NotNull ButtonType... buttonTypes) {
        getButtonTypes().setAll(buttonTypes);
        return this;
    }

    /**
     * Sets the button types to Ok only.
     * Shorthand for
     * {@snippet :
     *  this.withButtonTypes(ButtonType.OK);
     *}
     *
     * @return this instance.
     */
    @Contract("->this")
    public @NotNull EasyAlert buttonOkOnly() {
        return withButtonTypes(ButtonType.OK);
    }

    /**
     * Sets the button types to Yes, No or Cancel.
     * Shorthand for {@snippet :
     *     this.withButtonTypes(
     *          ButtonType.YES,
     *          ButtonType.NO,
     * ButtonType.CANCEL);}
     *
     * @return this instance.
     */
    @Contract("->this")
    public @NotNull EasyAlert buttonYesNoCancel() {
        return withButtonTypes(ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    }

    @Override
    public void run() {
        showAndWait();
    }

    /**
     * Invokes show and wait expecting a given user answer.
     * If said answer is the expected, will run onSuccess.
     *
     * @param expected  the expected value.
     * @param onSuccess what to do if expected value matches.
     */
    public void showAndExpect(@NotNull ButtonType expected,
                              @NotNull Runnable onSuccess) {
        showAndWait().filter(expected::equals).ifPresent(p -> onSuccess.run());
    }
}
