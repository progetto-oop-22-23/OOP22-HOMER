package homer.view;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import homer.App;
import homer.api.PoweredDeviceInfoImpl;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.lights.Light;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import homer.view.javafx.sensorsview.ElectricalMeterViewManager;
import homer.view.logger.LoggerImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
        var root = new AnchorPane();
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

        final FXMLLoader loader = new FXMLLoader();
        final URL url = App.class.getClassLoader()
                .getResource("homer/view/javafx/sensors/ElectricalMeterView.fxml");
        loader.setLocation(url);
        try {
            root = loader.load();
        } catch (IOException e) {
            final LoggerImpl logger = new LoggerImpl(System.err);
            logger.log(e.toString());
        }

        final ElectricalMeterViewManager view = loader.getController();
        view.setMeter(meter);
        final byte millis = 5;
        final byte hours = 2;
        final int frequency = 300;
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
            view.setLabels();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}
