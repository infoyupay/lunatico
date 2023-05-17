package com.yupay.lunatico.fxforms;

import javafx.fxml.FXMLLoader;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Utlity class / static factory for javafx forms.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public final class FxForms {
    /**
     * Private constructor to avoid instanciation.
     *
     * @throws IllegalAccessException always.
     */
    @Contract("->fail")
    private FxForms() throws IllegalAccessException {
        throw new IllegalAccessException("Never instancaite a static factory.");
    }

    /**
     * FxFactory for lunatico.fxml
     *
     * @return a new form controller.
     */
    public static LunaticoScene lunaticoScene() {
        return loadFxml("lunatico.fxml");
    }

    /**
     * FxFactory for user-view.fxml
     *
     * @return a new form controller.
     */
    public static FxUserView userView() {
        return loadFxml("user-view.fxml");
    }

    /**
     * FxFactory for user-card.fxml
     *
     * @return a new form controller.
     */
    public static FxUserCard userCard() {
        return loadFxml("user-card.fxml");
    }

    /**
     * FxFactory for password-change.fxml
     *
     * @return a new form controller.
     */
    public static FxPasswordChange passwordChange() {
        return loadFxml("password-change.fxml");
    }

    /**
     * FxFactory for user-login.fxml
     *
     * @return a new form controller.
     */
    public static FxUserLogin userLogin() {
        return loadFxml("user-login.fxml");
    }

    /**
     * FxFactory for movement-card.fxml
     *
     * @return a new form controller.
     */
    public static MovementCard movementCard() {
        return loadFxml("movement-card.fxml");
    }

    /**
     * FxFactory for movement-card.fxml
     *
     * @return a new form controller.
     */
    public static FxItemTrend itemTrend() {
        return loadFxml("item-trend.fxml");
    }

    /**
     * FxFactory for warehouse-view.fxml
     *
     * @return a new form controller.
     */
    public static FxWarehouseView warehouseView() {
        return loadFxml("warehouse-view.fxml");
    }

    /**
     * FxFactory for warehouse-card.fxml
     *
     * @return a new form controller.
     */
    public static FxWarehouseCard warehouseCard() {
        return loadFxml("warehouse-card.fxml");
    }

    /**
     * Loads the fxml controller based on the fxml definition.
     *
     * @param <T>  type erasure of controller.
     * @param fxml fxml file name as stated in package
     *             {@link com.yupay.lunatico.fxforms}
     */
    private static <T> T loadFxml(@NotNull String fxml) {
        try {
            var loader = new FXMLLoader(FxForms.class.getResource(fxml));
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to load " + fxml, e);
        }
    }
}
