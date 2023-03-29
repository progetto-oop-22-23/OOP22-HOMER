package homer.view.javafx;

import java.util.LinkedHashMap;
import java.util.Map;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.DeviceView;
import homer.controller.Controller;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.DeviceViewer;
import javafx.scene.layout.Pane;
import javafx.application.Platform;

public class JFXDeviceViewer extends Pane implements DeviceViewer {

    private Controller controller;
    private final Map<DeviceId, DeviceView> deviceMap = new LinkedHashMap<>();

    public JFXDeviceViewer(Controller controller) {
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
                    var deviceView = new TemperatureChangerView(deviceId, (TemperatureChangerState) deviceState,
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
                this.getChildren().remove(this.deviceMap.get(deviceId));
                deviceMap.remove(deviceId);
            }
        });
    }

    @Override
    public void start(Controller controller) {
        this.controller = controller;
    }
    
}
