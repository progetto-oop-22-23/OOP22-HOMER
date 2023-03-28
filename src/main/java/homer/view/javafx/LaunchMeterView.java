package homer.view.javafx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * TESTING CLASS!
 * Launches the view for ElectricalMeter
 */
public final class LaunchMeterView extends Application {

    private final List<Outlet> outlets = new ArrayList<>();

    private final ElectricalMeterImpl meter = new ElectricalMeterImpl(outlets);

    @Override
    public void start(final Stage primaryStage) throws Exception {
        final double cOutletState = 10.0;
        final double lOutletState = 15.0;
        Collections.addAll(outlets,
                OutletFactory.cOutlet(cOutletState),
                OutletFactory.cOutlet(cOutletState),
                OutletFactory.lOutlet(lOutletState),
                OutletFactory.lOutlet(lOutletState));
        meter.setOutlets(outlets);

        Scene scene = new Scene(new ElectricalMeterView(meter));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Electrical Meter");
        primaryStage.show();
    }
}
