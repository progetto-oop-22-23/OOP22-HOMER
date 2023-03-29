package homer.view.javafx.electricalmeter.scenebuilder;

import java.net.URL;
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

    @FXML
    // Reference of consumptionLabel for the FXML loader.
    private Label consumptionLabel;

    @FXML
    // Reference of energyLabel for the FXML loader.
    private Label energyLabel;

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
     * Empty constructor.
     */
    public ElectricalMeterViewManager() {
    }

    /**
     * Sets the {@link homer.controller.impl.electricalmeter}.
     * 
     * @param meter the meter.
     */
    public void setMeter(final ElectricalMeterImpl meter) {
        this.meter = meter;
    }

    @FXML
    private void init() {
    }

    private static int count = 0;

    /**
     * Sets the labels' values in the view.
     */
    @FXML
    public void setLabels() {
        final double consumption = meter.getGlobalConsumption();
        final double energy = meter.getAveragePower();
        outletTable.setItems(FXCollections.observableArrayList(meter.getOutlets()));
        consumptionLabel.setText(String.format("Global Consumption: %.2f W", consumption));
        energyLabel.setText(String.format("Average Energy: %.2f Wh", energy));
        outletStateColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<String>(
                        String.format("%.2f Wh", cellData.getValue().getState().getPower().get())));
        outletIDColumn.setCellValueFactory(
                cellData -> new ReadOnlyObjectWrapper<String>(
                        cellData.getValue().getClass().getSimpleName() + count++));
    }

}
