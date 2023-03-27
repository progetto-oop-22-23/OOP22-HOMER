package homer.view.javafx;

import java.util.List;

import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * {@link homer.controller.impl.electricalmeter.ElectricalMeterImpl} view.
 */
public final class ElectricalMeterView extends Application {

    private List<Outlet> outlets = List.of(); // JUST FOR TESTING!!!
    private final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        GridPane gridPane = new GridPane();
        Label powerConsumptionLabel = new Label();
        Label energyAbsorptionLabel = new Label();
        Label[] outletLabels = new Label[meter.getOutlets().size() + 1];

        for (int i = 0; i < outletLabels.length; i++) {
            outletLabels[i] = new Label("Outlet " + (i + 1) + " consumption: ");
        }

        Button addOutlet = new Button("+ Add Outlet");
        addOutlet.setOnAction(event -> {
            meter.addOutlet(OutletFactory.cOutlet(10.0));
            meter.computeConsumption();
            powerConsumptionLabel.setText("Power consumption: " + meter.getGlobalConsumption() + " kW");
            energyAbsorptionLabel.setText("Energy absorption: " + meter.getAveragePower() + " kW/h");
            outletLabels[outletLabels.length - 1].setText("Outlet " + outletLabels.length + " consumption: ");
        });

        Button removeOutlet = new Button("- Remove Outlet");
        removeOutlet.setOnAction(event -> {
            Outlet toRemove = meter.getOutlets().get(0);
            if (toRemove instanceof Outlet) {
                meter.removeOutlet(meter.getOutlets().get(0));
                outletLabels[0].setText("");
            }
        });

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // gridPane.add(powerConsumptionLabel, 0, 0);
        // gridPane.add(energyAbsorptionLabel, 1, 0);

        for (int i = 0; i < outletLabels.length - 1; i++) {
            gridPane.add(outletLabels[i], 0, i + 1);
        }

        VBox buttonBox = new VBox(10, addOutlet, removeOutlet);
        buttonBox.setAlignment(Pos.CENTER);
        gridPane.add(buttonBox, 2, 1, 1, outletLabels.length);

        // Add labels for the meter's power consumption and energy absorption
        /*
         * Label meterPowerLabel = new Label();
         * Label meterEnergyLabel = new Label();
         */

        gridPane.add(powerConsumptionLabel, 0, 0);
        gridPane.add(energyAbsorptionLabel, 0, 1);
        final int width = 400;
        final int height = 400;
        Scene scene = new Scene(gridPane, width, height);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Electrical Meter");
        primaryStage.show();
    }

}
