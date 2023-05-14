package com.yupay.lunatico.fxforms;


import com.yupay.lunatico.fxmview.FxUserMV;
import com.yupay.lunatico.fxtools.*;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.jetbrains.annotations.NotNull;

/**
 * The user's editor card form.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxUserCard extends Dialog<FxUserMV> implements CardDialog<FxUserMV> {
    /**
     * The currently editing value.
     */
    private final ObjectProperty<FxUserMV> value =
            new SimpleObjectProperty<>(this, "value");
    /**
     * Formatter for user ID.
     */
    private final TextFormatter<String> fmtID
            = LoginConverter.formatter();

    /**
     * The form editor mode of use.
     */
    private final ObjectProperty<EditorMode> formMode =
            new SimpleObjectProperty<>(this, "formMode");
    /**
     * The creator flag, it will be bound to formMode value.
     */
    private final ReadOnlyBooleanWrapper creator =
            new ReadOnlyBooleanWrapper(this, "creator");
    /**
     * The NOT creator flag, it will bound to formMode value.
     */
    private final ReadOnlyBooleanWrapper notCreator =
            new ReadOnlyBooleanWrapper(this, "notCreator");
    /**
     * The viewer flag, it will bound to formMode value.
     */
    private final ReadOnlyBooleanWrapper notViewer =
            new ReadOnlyBooleanWrapper(this, "notViewer");
    /**
     * The viewer flag, it will bound to formMode value.
     */
    private final ReadOnlyBooleanWrapper viewer =
            new ReadOnlyBooleanWrapper(this, "viewer");
    /**
     * FXML control injected from user-card.fxml
     */
    @FXML
    private DialogPane top;
    /**
     * FXML control injected from user-card.fxml
     */
    @FXML
    private TextField txtName;
    /**
     * FXML control injected from user-card.fxml
     */
    @FXML
    private CheckBox chkActive;
    /**
     * FXML control injected from user-card.fxml
     */
    @FXML
    private CheckBox chkRoleViewer;
    /**
     * FXML control injected from user-card.fxml
     */
    @FXML
    private CheckBox chkRoleAdmin;
    /**
     * FXML control injected from user-card.fxml
     */
    @FXML
    private CheckBox chkRoleReporter;
    /**
     * FXML control injected from user-card.fxml
     */
    @FXML
    private CheckBox chkRoleRoot;

    /**
     * Default constructor, it'll initialize some bindings.
     */
    public FxUserCard() {
        creator.bind(formModeProperty().isEqualTo(EditorMode.CREATOR));
        notCreator.bind(formModeProperty().isNotEqualTo(EditorMode.CREATOR));
        notViewer.bind(formModeProperty().isNotEqualTo(EditorMode.VIEWER));
        viewer.bind(formModeProperty().isEqualTo(EditorMode.VIEWER));
    }

    /**
     * FXML initialization.
     */
    @FXML
    void initialize() {
        setTitle("Editor de Usuario");
        setResultConverter(b -> b == ButtonType.OK ? getValue() : null);
        setDialogPane(top);
        valueProperty().addListener(new ValueChanged());
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #value}.get();
     */
    public final FxUserMV getValue() {
        return value.get();
    }

    @Override
    public final void setValue(@NotNull FxUserMV value) {
        this.value.set(value);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #value}.
     */
    public final ObjectProperty<FxUserMV> valueProperty() {
        return value;
    }

    /**
     * Public accessor - getter.
     *
     * @return value of {@link #fmtID}
     */
    public TextFormatter<String> getFmtID() {
        return fmtID;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #creator}.get();
     */
    public final boolean isCreator() {
        return creator.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #creator}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty creatorProperty() {
        return creator.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #notCreator}.get();
     */
    public final boolean isNotCreator() {
        return notCreator.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #notCreator}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty notCreatorProperty() {
        return notCreator.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #formMode}.get();
     */
    public final EditorMode getFormMode() {
        return formMode.get();
    }

    @Override
    public final void setFormMode(@NotNull EditorMode formMode) {
        this.formMode.set(formMode);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #formMode}.
     */
    public final ObjectProperty<EditorMode> formModeProperty() {
        return formMode;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #notViewer}.get();
     */
    public final boolean isNotViewer() {
        return notViewer.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #notViewer}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty notViewerProperty() {
        return notViewer.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #viewer}.get();
     */
    public final boolean isViewer() {
        return viewer.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #viewer}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty viewerProperty() {
        return viewer.getReadOnlyProperty();
    }

    @Override
    public @NotNull FxUserMV blank() {
        var x = new FxUserMV();
        x.setActive(true);
        return x;
    }

    @Override
    public @NotNull Dialog<FxUserMV> getRoot() {
        return this;
    }

    /**
     * Listener to keep track of bindings when value changes.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class ValueChanged implements CardValueListener<FxUserMV> {
        @Override
        public void bind(@NotNull FxUserMV value) {
            fmtID.valueProperty().bindBidirectional(value.idProperty());
            txtName.textProperty().bindBidirectional(value.realNameProperty());
            chkActive.selectedProperty().bindBidirectional(value.activeProperty());
            chkRoleAdmin.selectedProperty().bindBidirectional(value.roleAdminProperty());
            chkRoleReporter.selectedProperty().bindBidirectional(value.roleReporterProperty());
            chkRoleRoot.selectedProperty().bindBidirectional(value.roleAdminSysProperty());
            chkRoleViewer.selectedProperty().bindBidirectional(value.roleViewerProperty());
        }

        @Override
        public void clear() {
            new ClearFacility().add(fmtID)
                    .add(txtName)
                    .add(chkActive)
                    .add(chkRoleAdmin)
                    .add(chkRoleReporter)
                    .add(chkRoleRoot)
                    .add(chkRoleViewer)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxUserMV value) {
            fmtID.valueProperty().unbindBidirectional(value.idProperty());
            txtName.textProperty().unbindBidirectional(value.realNameProperty());
            chkActive.selectedProperty().unbindBidirectional(value.activeProperty());
            chkRoleAdmin.selectedProperty().unbindBidirectional(value.roleAdminProperty());
            chkRoleReporter.selectedProperty().unbindBidirectional(value.roleReporterProperty());
            chkRoleRoot.selectedProperty().unbindBidirectional(value.roleAdminSysProperty());
            chkRoleViewer.selectedProperty().unbindBidirectional(value.roleViewerProperty());
        }
    }

}
