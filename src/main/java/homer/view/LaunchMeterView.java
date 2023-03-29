package homer.view;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import homer.App;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import homer.view.javafx.electricalmeter.scenebuilder.ElectricalMeterViewManager;
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
                OutletFactory.lOutlet(lOutletState));
        ElectricalMeterImpl meter = new ElectricalMeterImpl(outlets);

        FXMLLoader loader = new FXMLLoader();
        URL url = App.class.getClassLoader()
                .getResource("homer/view/javafx/electricalmeter/scenebuilder/ElectricalMeterView.fxml");
        System.out.println(url);
        loader.setLocation(url);
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ElectricalMeterViewManager view = loader.getController();
        view.setMeter(meter);
        meter.updateTick(Duration.ofHours(1));
        view.setLabels();
        // Create a scene and show the stage
        scene = new Scene(root);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

}
