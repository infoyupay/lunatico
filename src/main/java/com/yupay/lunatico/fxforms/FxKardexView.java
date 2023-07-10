package com.yupay.lunatico.fxforms;

import com.yupay.lunatico.dao.DAOFactory;
import com.yupay.lunatico.dao.DataSource;
import com.yupay.lunatico.fxmview.*;
import com.yupay.lunatico.fxtools.CardValueListener;
import com.yupay.lunatico.fxtools.ClearFacility;
import com.yupay.lunatico.fxtools.NumberStringBinding;
import com.yupay.lunatico.fxtools.ValueFactoryManager;
import com.yupay.lunatico.model.Item;
import com.yupay.lunatico.model.MovementType;
import jakarta.persistence.EntityManager;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Controller class for the Kardex View system.
 *
 * @author InfoYupay SACS
 * @version 1.0
 */
public class FxKardexView {
    /**
     * The data to show in table view.
     */
    private final ObservableList<FxKardexDetailMV> data
            = FXCollections.observableArrayList();

    /**
     * Holds true if the before or after date are inconsistent to perform query.
     */
    private final ReadOnlyBooleanWrapper missingData =
            new ReadOnlyBooleanWrapper(this, "missingData", true);
    /**
     * The item property.
     */
    private final ObjectProperty<FxItemMV> item =
            new SimpleObjectProperty<>(this, "item");
    /**
     * The warehouse property.
     */
    private final ObjectProperty<FxWarehouseMV> warehouse =
            new SimpleObjectProperty<>(this, "warehouse");
    /**
     * The balance overview.
     */
    private final ObjectProperty<FxOvBalanceMV> balance =
            new SimpleObjectProperty<>(this, "balance");
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colBeforeCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colBeforeUnitCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colBeforeUnits;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, LocalDate> colDate;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colEndCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colEndUnitCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colEndUnits;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, String> colFolioNumber;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, String> colFolioSerie;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, String> colFolioType;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colInputCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colInputUnitCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colInputUnits;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, Long> colMovID;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, MovementType> colMovType;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colOutCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colOutUnitCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableColumn<FxKardexDetailMV, BigDecimal> colOutUnits;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private DatePicker dtpFrom;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private DatePicker dtpUntil;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Label lblBalanceCost;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Label lblBalanceQty;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Label lblBalanceUnitPrice;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Label lblItemType;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Hyperlink lnkItem;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Hyperlink lnkUnit;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Hyperlink lnkWarehouse;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private TableView<FxKardexDetailMV> tblKardex;
    /**
     * FXML control injected from kardex-view.fxml
     */
    @FXML
    private Stage top;

    /**
     * FXML initializer.
     */
    @FXML
    void initialize() {
        new ValueFactoryManager<FxKardexDetailMV>()
                .addLong(colMovID, FxKardexDetailMV::idProperty)
                .add(colBeforeCost, FxKardexDetailMV::beforeCostProperty)
                .add(colBeforeUnitCost, FxKardexDetailMV::beforePriceProperty)
                .add(colBeforeUnits, FxKardexDetailMV::beforeQuantityProperty)
                .add(colDate, FxKardexDetailMV::docDateProperty)
                .add(colEndCost, FxKardexDetailMV::balanceCostProperty)
                .add(colEndUnitCost, FxKardexDetailMV::balancePriceProperty)
                .add(colEndUnits, FxKardexDetailMV::balanceQuantityProperty)
                .add(colFolioNumber, FxKardexDetailMV::folioNumberProperty)
                .add(colFolioSerie, FxKardexDetailMV::folioSerieProperty)
                .add(colFolioType, FxKardexDetailMV::folioTypeNameProperty)
                .add(colInputCost, FxKardexDetailMV::inCostProperty)
                .add(colInputUnitCost, FxKardexDetailMV::inPriceProperty)
                .add(colInputUnits, FxKardexDetailMV::inQuantityProperty)
                .add(colMovType, FxKardexDetailMV::typeProperty)
                .add(colOutCost, FxKardexDetailMV::outCostProperty)
                .add(colOutUnitCost, FxKardexDetailMV::outPriceProperty)
                .add(colOutUnits, FxKardexDetailMV::outQuantityProperty)
                .provide();
        missingData.bind(Bindings.createBooleanBinding(
                this::checkMissingData,
                dtpFrom.valueProperty(),
                dtpUntil.valueProperty()));

        itemProperty().addListener(new OnItemChanged());
        warehouseProperty().addListener(new OnWarehouseChange());
        balanceProperty().addListener(new OnBalanceChange());

        tblKardex.setItems(data);
    }

    /**
     * Checks if date from or until are missing,
     * also checks if until is before from.
     *
     * @return true if data is missing or invalid.
     */
    private boolean checkMissingData() {
        var from = dtpFrom.getValue();
        var until = dtpUntil.getValue();
        return from == null
                || until == null
                || !until.isAfter(from);
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onRefreshAction() {
        var from = dtpFrom.getValue();
        var until = dtpUntil.getValue();
        try (var em = DataSource.em()) {
            fetchBalance(em);
            data.clear();
            DAOFactory.kardexDetail()
                    .listWithinDates(from, until,
                            getItem().toModel(),
                            getWarehouse().toModel(),
                            em)
                    .map(FxKardexDetailMV::new).forEach(data::add);
        } catch (RuntimeException e) {
            new ErrorHandler().withBriefing("Ocurrió un error al obtener el listado de movimientos.").accept(e);
        }
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onShown() {
        try (var em = DataSource.em()) {
            fetchBalance(em);
        } catch (RuntimeException e) {
            new ErrorHandler()
                    .withBriefing("Ocurrió un error al obtener el saldo local.")
                    .accept(e);
        }
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onItemAction() {
        FxForms.itemCard().viewer(top, getItem());
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onWarehouseAction() {
        FxForms.warehouseCard().viewer(top, getWarehouse());
    }

    /**
     * FXML event handler.
     */
    @FXML
    void onUnitsAction() {
        FxForms.unitCard().viewer(top, getItem().getUnit());
    }

    /**
     * FXML event handler.
     *
     * @param event the event object.
     */
    @FXML
    void onTableClicked(@NotNull MouseEvent event) {
        //If click count is less than 2, ignore event.
        if (event.isConsumed() || event.getClickCount() < 2) return;
        //Consume event.
        event.consume();
        //Retrieve selection.
        var sel = tblKardex.getSelectionModel().getSelectedItem();
        //If selection is null, ignore event.
        if (sel == null) return;
        //Variable to hold movement, don't change.
        FxMovementMV mov = null;
        //Get the entity manager.
        try (var em = DataSource.em()) {
            //Fetch movement from selected row.
            var movement = DAOFactory.movement().fetchOne(em, sel.getMovement());
            //Send movement to variable.
            mov = new FxMovementMV(movement);
            //DON'T SHOW CARD IN THIS PART, WE DON'T WANT TO KEEP ENTITY MANAGER OPEN!
        } catch (RuntimeException e) {
            new ErrorHandler()
                    .withBriefing("Ocurrió un error al recuperar el registro de movimiento.")
                    .accept(e);
        }
        //If everything went fine, mov should be not null, and must be sent to viewer.
        if (mov != null) FxForms.movementCard().viewer(top, mov);
    }

    /**
     * Convenient method to fetch the local balance of item.
     *
     * @param em the entity manager object.
     */
    private void fetchBalance(@NotNull EntityManager em) {
        var balance = DAOFactory
                .balanceOverview()
                .fetchOne(em, getWarehouse().toModel(), getItem().toModel());
        em.refresh(balance);
        setBalance(new FxOvBalanceMV(balance));
    }

    /**
     * Shows the form in a non-modal window.
     *
     * @param owner the owner window (usually primary stage).
     */
    public void show(@Nullable Stage owner) {
        if (owner != null) {
            top.initOwner(owner);
        }
        top.initModality(Modality.NONE);
        top.show();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #missingData}.get();
     */
    public final boolean isMissingData() {
        return missingData.get();
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #missingData}.
     */
    @SuppressWarnings("unused")
    public final ReadOnlyBooleanProperty missingDataProperty() {
        return missingData.getReadOnlyProperty();
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #item}.get();
     */
    public final FxItemMV getItem() {
        return item.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param item value to assign into {@link #item}.
     */
    public final void setItem(FxItemMV item) {
        this.item.set(item);
    }

    /**
     * Fetches from DAO layer and sets the item.
     *
     * @param item the item ID.
     */
    public final void fetchAndSet(long item) {
        try (var em = DataSource.em()) {
            Optional.ofNullable(em.find(Item.class, item))
                    .map(FxItemMV::new)
                    .ifPresentOrElse(this::setItem,
                            EasyAlert.warning()
                                    .withTitle("¡ATENCIÓN!")
                                    .withHeaderText("Artículo no hallado en la base de datos.")
                                    .withContentText("Lo siento, he buscado el artículo seleccionado en la base " +
                                            "de datos pero he sido incapaz de hallarlo. Por favor, verifica el " +
                                            "estado del sistema.")
                                    .buttonOkOnly());
        } catch (RuntimeException e) {
            new ErrorHandler()
                    .withBriefing("Error al recuperar el artículo de la base de datos.")
                    .accept(e);
        }
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #item}.
     */
    public final ObjectProperty<FxItemMV> itemProperty() {
        return item;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #warehouse}.get();
     */
    public final FxWarehouseMV getWarehouse() {
        return warehouse.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param warehouse value to assign into {@link #warehouse}.
     */
    public final void setWarehouse(FxWarehouseMV warehouse) {
        this.warehouse.set(warehouse);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #warehouse}.
     */
    public final ObjectProperty<FxWarehouseMV> warehouseProperty() {
        return warehouse;
    }

    /**
     * FX Accessor - getter.
     *
     * @return value of {@link #balance}.get();
     */
    public final FxOvBalanceMV getBalance() {
        return balance.get();
    }

    /**
     * FX Accessor - setter.
     *
     * @param balance value to assign into {@link #balance}.
     */
    public final void setBalance(FxOvBalanceMV balance) {
        this.balance.set(balance);
    }

    /**
     * FX Accessor - property.
     *
     * @return property {@link #balance}.
     */
    public final ObjectProperty<FxOvBalanceMV> balanceProperty() {
        return balance;
    }

    /**
     * Bindings keeper for item.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class OnItemChanged
            implements CardValueListener<FxItemMV> {
        @Override
        public void bind(@NotNull FxItemMV value) {
            lnkItem.textProperty().bind(value.nameProperty());
            lnkUnit.textProperty().bind(value.unitProperty().asString());
            lblItemType.textProperty().bind(value.typeProperty().asString());
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lnkItem)
                    .add(lnkUnit)
                    .add(lblItemType)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxItemMV value) {
            lnkItem.textProperty().unbind();
            lnkUnit.textProperty().unbind();
            lblItemType.textProperty().unbind();
        }
    }

    /**
     * Bindings keeper for warehouse.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class OnWarehouseChange
            implements CardValueListener<FxWarehouseMV> {
        @Override
        public void bind(@NotNull FxWarehouseMV value) {
            lnkWarehouse.textProperty().bind(value.nameProperty());
        }

        @Override
        public void clear() {
            lnkWarehouse.setText("");
        }

        @Override
        public void unbind(@NotNull FxWarehouseMV value) {
            lnkWarehouse.textProperty().unbind();
        }
    }

    /**
     * Binding keeper for balance overview.
     *
     * @author InfoYupay SACS
     * @version 1.0
     */
    private class OnBalanceChange
            implements CardValueListener<FxOvBalanceMV> {
        @Override
        public void bind(@NotNull FxOvBalanceMV value) {
            lblBalanceCost.textProperty()
                    .bind(NumberStringBinding.forMonetized(value.balanceCostProperty()));
            lblBalanceQty.textProperty()
                    .bind(NumberStringBinding.forWarehouse(value.balanceUnitsProperty()));
            lblBalanceUnitPrice.textProperty()
                    .bind(NumberStringBinding.forMonetized(value.balanceUnitCostProperty()));
        }

        @Override
        public void clear() {
            new ClearFacility()
                    .add(lblBalanceCost, lblBalanceQty, lblBalanceUnitPrice)
                    .clear();
        }

        @Override
        public void unbind(@NotNull FxOvBalanceMV value) {
            lblBalanceCost.textProperty().unbind();
            lblBalanceQty.textProperty().unbind();
            lblBalanceUnitPrice.textProperty().unbind();
        }
    }
}
