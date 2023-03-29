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
import homer.model.outlets.OutletState;
import homer.view.javafx.electricalmeter.scenebuilder.ElectricalMeterViewManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
            System.exit(0);
        });
        var root = new BorderPane();
        Scene scene = new Scene(root, INITIAL_W, INITIAL_H);
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
        ElectricalMeterImpl meter = new ElectricalMeterImpl(outlets);

        FXMLLoader loader = new FXMLLoader();
        URL url = App.class.getClassLoader()
                .getResource("homer/view/javafx/electricalmeter/scenebuilder/ElectricalMeterView.fxml");
        loader.setLocation(url);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ElectricalMeterViewManager view = loader.getController();
        view.setMeter(meter);
        final long millis = 5;
        final long hours = 2;
        scene = new Scene(root);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
        Timeline timeline = new Timeline(new KeyFrame(javafx.util.Duration.millis(300), event -> {
            for (int i = 0; i < lights.size(); i++) {
                Light light = lights.get(i);
                Outlet outlet = light.getPowerInfo().getOutlet();
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
