package homer.view;

import homer.api.DeviceIdImpl;
import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.view.javafx.AddDevicesView;
import homer.view.javafx.TemperatureChangerView;
import homer.core.SimManagerImpl;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.sim.SimManagerViewFxImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main javaFX application.
 */
public class JFXApplication extends Application {
    private static final long INITIAL_W = 300;
    private static final long INITIAL_H = 300;

    @Override
    public final void start(Stage stage) throws Exception {
        final Group root = new Group();
        final Scene scene = new Scene(root, INITIAL_W, INITIAL_H);
        final VBox vBox = new VBox();
        final Controller controller = new ControllerImpl();
        var d = new TemperatureChangerView(new DeviceIdImpl(), new TemperatureChangerState().addCurrentIntensity(1).addMinIntensity(0).addMaxIntensity(10), controller);
        root.getChildren().add(vBox);
        vBox.getChildren().addAll(new AddDevicesView(controller), d);

        final var simManager = new SimManagerImpl(controller);
        Platform.runLater(() -> {
            final var simView = new SimManagerViewFxImpl();
            simView.setObserver(simManager);
        });

        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();
    }


}
