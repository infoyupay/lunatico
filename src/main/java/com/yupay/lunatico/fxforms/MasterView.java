package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.fxtools.Functionals;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Abstraction layer to reuse code for Master view forms.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public abstract class MasterView {
    /**
     * Holds true if table view selection is empty.
     */
    private final ReadOnlyBooleanWrapper emptySelection =
            new ReadOnlyBooleanWrapper(this, "emptySelection");
    /**
     * Collection of external disabled properties to update when leaving this view.
     */
    private List<BooleanProperty> externalDisabled;

    /**
     * FXML event handler when stage is shown.
     */
    @FXML
    public void onStageShown() {
        if (externalDisabled != null) externalDisabled.forEach(Functionals.setValue(true));
        onRefreshDBAction();
    }

    /**
     * FXML event handler when stage is closed.
     */
    @FXML
    public void onStageClosed() {
        if (externalDisabled != null) externalDisabled.forEach(Functionals.setValue(false));
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #emptySelection}.get();
     */
    public final boolean isEmptySelection() {
        return emptySelection.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #emptySelection}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty emptySelectionProperty() {
        return emptySelection.getReadOnlyProperty();
    }


    /**
     * Initializes the window owner, and then
     * window moadlity to APPLICATION_MODAL,
     * and then shows and wait.
     *
     * @param externalDisabled external boolean properties to disable controls
     *                         while this windows is kept open.
     * @see Stage#showAndWait()
     */
    public void showAndWait(@Nullable BooleanProperty... externalDisabled) {
        if (externalDisabled != null) this.externalDisabled = Arrays.asList(externalDisabled);
        getTop().addEventHandler(WindowEvent.ANY, FxLunatico.APP_CONTROLLER);
        getTop().initOwner(FxLunatico.PRIMARY);
        getTop().initModality(Modality.NONE);
        getTop().showAndWait();
    }


    /**
     * FXML event handler to capture double clicks on table data.
     *
     * @param event the event object.
     */
    @FXML
    void onTableDataClicked(@NotNull MouseEvent event) {
        if (!event.isConsumed()
                && event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() > 1) {
            event.consume();
            onEditRowAction();
        }
    }

    /**
     * Binds the empty selection property to a given table view.
     *
     * @param table the table to observe.
     */
    protected void bindEmptySelection(@NotNull TableView<?> table) {
        emptySelection.bind(table.getSelectionModel().selectedItemProperty().isNull());
    }

    /**
     * FXML event handler when user requests refresh DB action.
     */
    abstract void onRefreshDBAction();

    /**
     * Accessor to the top stage.
     *
     * @return the top stage.
     */
    protected abstract @NotNull Stage getTop();

    /**
     * FXML event handler to edit a row.
     */
    abstract void onEditRowAction();

}
