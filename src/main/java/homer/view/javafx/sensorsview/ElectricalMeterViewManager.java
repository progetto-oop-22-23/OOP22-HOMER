package homer.view.javafx.sensorsview;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * View manager for {@link homer.controller.impl.electricalmeter}.
 */
public final class ElectricalMeterViewManager {

    private ElectricalMeterImpl meter;

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

    @FXML
    // Reference of removeOutlet for the FXML loader.
    private Button addOutlet;

    @FXML
    // Reference of removeOutlet for the FXML loader.
    private Button removeOutlet;

    // Automatically set by FXML Loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    /**
     * Empty constructor.
     */
    public ElectricalMeterViewManager() {
        this.meter = new ElectricalMeterImpl();
    }

    /**
     * Sets the {@link homer.controller.impl.electricalmeter}.
     * 
     * @param meter the meter.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
        justification = "Meter HAS to be exactly the same object (can't be a protective copy)")
    public void setMeter(final ElectricalMeterImpl meter) {
        Objects.requireNonNull(meter);
        this.meter = meter;
    }

    /**
     * Returns the {@code ElectricalMeter} that controls the view.
     * 
     * @return the {@code ElectricalMeter}.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP",
        justification = "Meter HAS to be exactly the same object (can't be a protective copy)")
    public ElectricalMeterImpl getMeter() {
        return this.meter;
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
                        cellData.getValue().getClass().getSimpleName()));
    }

    /**
     * Adds an Outlet.
     */
    @FXML
    public void addOutlet() {
        meter.addOutlet(OutletFactory.lOutlet(0.0));
        outletTable.setItems(FXCollections.observableArrayList(meter.getOutlets()));
        this.setLabels();
    }

    /**
     * Removes an Outlet.
     */
    @FXML
    public void removeOutlet() {
        meter.removeOutlet(meter.getOutlets().get(meter.getOutlets().size() - 1));
        outletTable.setItems(FXCollections.observableArrayList(meter.getOutlets()));
        this.setLabels();
    }
}
