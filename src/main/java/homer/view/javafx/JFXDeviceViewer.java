package homer.view.javafx;

import java.util.LinkedHashMap;
import java.util.Map;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.DeviceView;
import homer.controller.Controller;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.DeviceViewer;
import homer.view.javafx.deviceview.TemperatureChangerView;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

/**
 * Main view for {@link Device}s.
 */
public final class JFXDeviceViewer extends VBox implements DeviceViewer {

    private Controller controller;
    private final Map<DeviceId, DeviceView> deviceMap = new LinkedHashMap<>();

    /**
     * 
     * @param controller the controller that will receive updates.
     */
    public JFXDeviceViewer(final Controller controller) {
        this.controller = controller;
        this.getChildren().add(new AddDevicesView(controller));
    }

    @Override
    public void updateDeviceState(final DeviceId deviceId, final DeviceState deviceState) {
        Platform.runLater(() -> {
            if (this.deviceMap.containsKey(deviceId)) {
                final var deviceView = this.deviceMap.get(deviceId);
                deviceView.setState(deviceState);
            } else {
                if (deviceState instanceof TemperatureChangerState) {
                    final var deviceView = new TemperatureChangerView(deviceId, (TemperatureChangerState) deviceState,
                            controller);
                    this.deviceMap.put(deviceId, deviceView);
                    this.getChildren().add(deviceView);
                }
            }
        });
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        Platform.runLater(() -> {
        if (deviceMap.containsKey(deviceId)) {
            final var target = deviceMap.get(deviceId);
            // since all javaFX components (and derived classes) we are using extend Node,
            // this is fine
            this.getChildren().remove((Node) target);
            deviceMap.remove(deviceId);
        }
        });
    }

    @Override
    public void start(final Controller controller) {
        this.controller = controller;
    }

}
