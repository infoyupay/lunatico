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
    @Contract("-> new")
    public static @NotNull LunaticoScene lunaticoScene() {
        return loadFxml("lunatico.fxml");
    }

    /**
     * FxFactory for user-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxUserView userView() {
        return loadFxml("user-view.fxml");
    }

    /**
     * FxFactory for user-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxUserCard userCard() {
        return loadFxml("user-card.fxml");
    }

    /**
     * FxFactory for password-change.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxPasswordChange passwordChange() {
        return loadFxml("password-change.fxml");
    }

    /**
     * FxFactory for user-login.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxUserLogin userLogin() {
        return loadFxml("user-login.fxml");
    }

    /**
     * FxFactory for movement-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxMovementCard movementCard() {
        return loadFxml("movement-card.fxml");
    }

    /**
     * FxFactory for kardex-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxKardexView kardexView() {
        return loadFxml("kardex-view.fxml");
    }

    /**
     * FxFactory for movement-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxItemTrend itemTrend() {
        return loadFxml("item-trend.fxml");
    }

    /**
     * FxFactory for warehouse-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxWarehouseView warehouseView() {
        return loadFxml("warehouse-view.fxml");
    }

    /**
     * FxFactory for warehouse-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxWarehouseCard warehouseCard() {
        return loadFxml("warehouse-card.fxml");
    }

    /**
     * FxFactory for unit-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxUnitCard unitCard() {
        return loadFxml("unit-card.fxml");
    }

    /**
     * FxFactory for unit-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxUnitView unitView() {
        return loadFxml("unit-view.fxml");
    }

    /**
     * FxFactory for item-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxItemCard itemCard() {
        return loadFxml("item-card.fxml");
    }

    /**
     * FxFactory for item-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxItemView itemView() {
        return loadFxml("item-view.fxml");
    }

    /**
     * FxFactory for folio_type-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxFolioTypeCard folioTypeCard() {
        return loadFxml("folio_type-card.fxml");
    }

    /**
     * FxFactory for folio_type-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxFolioTypeView folioTypeView() {
        return loadFxml("folio_type-view.fxml");
    }

    /**
     * FxFactory for person-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxPersonCard personCard() {
        return loadFxml("person-card.fxml");
    }

    /**
     * FxFactory for person-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxPersonView personView() {
        return loadFxml("person-view.fxml");
    }

    /**
     * FxFactory for movement_line-card.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxMovementLnCard movementLineCard() {
        return loadFxml("movement_line-card.fxml");
    }

    /**
     * FxFactory for snapshot-view.fxml
     *
     * @return a new form controller.
     */
    @Contract("-> new")
    public static @NotNull FxSnapshotView snapshotView() {
        return loadFxml("snapshot-view.fxml");
    }

    /**
     * FxFactory for search-card.fxml
     *
     * @param <T> type erasure of searched element.
     * @return a new search card controller.
     */
    @Contract("->new")
    public static <T> @NotNull FxSearchCard<T> searchCard() {
        return loadFxml("search-card.fxml");
    }

    /**
     * Loads the fxml controller based on the fxml definition.
     *
     * @param <T>  type erasure of controller.
     * @param fxml fxml file name as stated in package
     *             {@link com.yupay.lunatico.fxforms}
     */
    private static <T> @NotNull T loadFxml(@NotNull String fxml) {
        try {
            var loader = new FXMLLoader(FxForms.class.getResource(fxml));
            loader.load();
            return loader.getController();
        } catch (IOException e) {
            throw new UncheckedIOException("Unable to load " + fxml, e);
        }
    }
}
