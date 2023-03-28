package homer.view.javafx;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LaunchMeterView extends Application {

    private final List<Outlet> outlets = new ArrayList<>();

    private final ElectricalMeterImpl meter = new ElectricalMeterImpl(outlets);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Collections.addAll(outlets,
                OutletFactory.cOutlet(10.0),
                OutletFactory.cOutlet(10.0),
                OutletFactory.lOutlet(15.0),
                OutletFactory.lOutlet(15.0));
        meter.setOutlets(outlets);

        Scene scene = new Scene(new ElectricalMeterView(meter));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Electrical Meter");
        primaryStage.show();
        /*
         * while(true) {
         * meter.updateTick(Duration.ofHours(1));
         * ElectricalMeterView view = (ElectricalMeterView) scene.getRoot();
         * view.updateUI();
         * }
         */

        // Create a Timeline to update the meter and UI every hour

        

    }
}
