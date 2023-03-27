package homer.view;

import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.javafx.AddDevicesView;
import homer.core.SimManagerImpl;
import homer.view.sim.SimManagerViewFxImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JFXApplication extends Application {
    private static final long INITIAL_W = 300;
    private static final long INITIAL_H = 300;

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, INITIAL_W, INITIAL_H);
        VBox vBox = new VBox();
        final var state = new TemperatureChangerState().addCurrentIntensity(3.0).addMaxIntensity(10).addMinIntensity(1);
        Controller controller = new ControllerImpl();
        root.getChildren().addAll(vBox, new AddDevicesView(controller));

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
