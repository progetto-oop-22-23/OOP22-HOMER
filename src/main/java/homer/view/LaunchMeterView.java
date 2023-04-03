package homer.view;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import homer.api.PoweredDeviceInfoImpl;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.lights.Light;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import homer.view.javafx.sensorsview.ElectricalMeterViewManager;
import homer.view.javafx.sensorsview.SensorDashboardViewManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * JUST FOR TESTING PURPOSES.
 */
public final class LaunchMeterView extends Application {

    private static final long INITIAL_W = 300;
    private static final long INITIAL_H = 300;
    private static final String TITLE = "HOMER - Electrical Meter";

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setOnCloseRequest(event -> {
            Platform.exit();
        });
        // Load the first FXML file
        final FXMLLoader dashboardLoader = new FXMLLoader(
            getClass().getResource("/homer/view/javafx/sensors/SensorDashboardView.fxml"));
        final FXMLLoader meterLoader = new FXMLLoader(
            getClass().getResource("/homer/view/javafx/sensors/ElectricalMeterView.fxml"));
        final BorderPane root = dashboardLoader.load();

        // Load the second FXML file into the second tab
        // FXMLLoader fxmlLoader2 = new
        // FXMLLoader(getClass().getResource("path/to/second/fxml/file.fxml"));
        // root.getCenter().lookup("#meterTab").setContent(fxmlLoader2.load());
        final TabPane tabPane = (TabPane) root.getCenter();
        final ObservableList<Tab> tabs = tabPane.getTabs();

        for (final Tab tab : tabs) {
            final String id = "meterTab";
            if (tab.getId().equals(id)) {
                tab.setContent(meterLoader.load());
                break;
            }
        }
        // Set the controller for the first FXML file
        // dashboardLoader.<SensorDashboardViewManager>getController().init();
        // meterLoader.<ElectricalMeterViewManager>getController().init();

        final double cOutletState = 10.0;
        final double lOutletState = 15.0;
        final List<Outlet> outlets = new ArrayList<>();
        Collections.addAll(outlets,
                OutletFactory.cOutlet(cOutletState),
                OutletFactory.cOutlet(cOutletState),
                OutletFactory.lOutlet(lOutletState),
                OutletFactory.lOutlet(lOutletState),
                OutletFactory.lOutlet(lOutletState),
                OutletFactory.cOutlet(cOutletState));
        final List<Light> lights = new ArrayList<>();
        final double maxLightConsumption = 15.0;
        for (final Outlet outlet : outlets) {
            lights.add(new Light(true, new PoweredDeviceInfoImpl(maxLightConsumption, outlet)));
        }
        final ElectricalMeterImpl meter = new ElectricalMeterImpl(outlets);
        final SensorDashboardViewManager dashboard = dashboardLoader.getController();
        final ElectricalMeterViewManager meterView = meterLoader.getController();

        dashboard.setMeter(meter);
        meterView.setMeter(meter);
        final byte millis = 5;
        final byte hours = 2;
        final int frequency = 300; // ms
        final Scene scene = new Scene(root, INITIAL_W, INITIAL_H);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
        final Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(frequency), event -> {
            for (final Light light : lights) {
                final Outlet outlet = light.getPowerInfo().getOutlet();
                light.updateTick(Duration.ofMillis(millis));
                outlet.getState().addValue(light.getInstantConsumption());
                outlet.updateTick(Duration.ofHours(hours));
            }
            meter.updateTick(Duration.ofHours(hours));
            meterView.setLabels();
            dashboard.setLabels();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
