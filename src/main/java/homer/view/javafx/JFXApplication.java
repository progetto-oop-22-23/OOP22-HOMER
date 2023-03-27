package homer.view.javafx;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.DeviceView;
import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.core.SimManagerImpl;
import homer.view.View;
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
public class JFXApplication extends Application implements View {
    private static final long INITIAL_W = 300;
    private static final long INITIAL_H = 300;
    private final Map<DeviceId, DeviceView> map = new LinkedHashMap<>();
    private Controller controller;

    /**
     * 
     * @param controller the controller to be added
     */
    public JFXApplication(final Controller controller) {
        Objects.requireNonNull(controller);
        this.controller = controller;
    }

    @Override
    public final void start(Stage stage) throws Exception {
        final Group root = new Group();
        final Scene scene = new Scene(root, INITIAL_W, INITIAL_H);
        final VBox vBox = new VBox();
        final Controller controller = new ControllerImpl();
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

    @Override
    public void updateDeviceState(DeviceId deviceId, DeviceState deviceState) {
        if (map.containsKey(deviceId)) {
            final var targetDeviceView = map.get(deviceId);
            targetDeviceView.setState(deviceState);
        }
    }

    @Override
    public void removeDevice(DeviceId deviceId) {
        if (map.containsKey(deviceId)) {
            map.remove(deviceId);
        }
    }

    @Override
    public void start(Controller controller) {
        Objects.requireNonNull(controller);
        this.controller = controller;
    }

}
