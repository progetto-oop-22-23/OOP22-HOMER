package homer.view.javafx.sensorsview;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * View manager for {@link homer.controller.impl.electricalmeter}.
 */
public final class ElectricalMeterViewManager {

    // Used to print no. of Outlet in labels.
    private static int outletNumber;

    @FXML
    // Reference of consumptionLabel for the FXML loader.
    private Label consumptionLabel;

    @FXML
    // Reference of powerLabel for the FXML loader.
    private Label powerLabel;

    @FXML
    private TableView<Outlet> outletTable;

    @FXML
    // Reference of outletStateColumn for the FXML loader.
    private TableColumn<Outlet, String> outletStateColumn;

    @FXML
    // Reference of outletStateColumn for the FXML loader.
    private TableColumn<Outlet, String> outletIDColumn;

    // Automatically set by FXML Loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    private ElectricalMeterImpl meter;

    /**
     * Constructor for {@link homer.view.javafx.sensorsview.ElectricalMeterViewManager}.
     * @param meter the {@link homer.controller.impl.electricalmeter.ElectricalMeterImpl} to use.
     */
    public ElectricalMeterViewManager(final ElectricalMeterImpl meter) {
        this.meter = new ElectricalMeterImpl(meter.getOutlets());
    }

    /**
     * Empty constructor.
     */
    public ElectricalMeterViewManager() {
        this.meter = null;
    }

    /**
     * Sets the {@link homer.controller.impl.electricalmeter}.
     * 
     * @param meter the meter.
     */
    public void setMeter(final ElectricalMeterImpl meter) {
        Objects.requireNonNull(meter);
        this.meter = new ElectricalMeterImpl(meter.getOutlets());
    }

    /**
     * Sets the labels' values in the view.
     */
    @FXML
    public void setLabels() {
        final double consumption = meter.getGlobalConsumption();
        final double power = meter.getAveragePower();
        outletTable.setItems(FXCollections.observableArrayList(meter.getOutlets()));
        consumptionLabel.setText(String.format("Global Consumption: %.2f Wh", consumption));
        powerLabel.setText(String.format("Instant power: %.2f W", power));
        outletStateColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<String>(
                        String.format("%.2f Wh", cellData.getValue().getState().getPower().get())));
        outletIDColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<String>(
                        cellData.getValue().getClass().getSimpleName() + outletNumber++));
    }

}
