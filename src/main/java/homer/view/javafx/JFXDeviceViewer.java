package homer.view.javafx;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.state.ActuatedDeviceState;
import homer.api.state.LockState;
import homer.api.state.ThermometerState;
import homer.controller.Controller;
import homer.model.airquality.AirQualityState;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.DeviceViewer;
import homer.view.javafx.deviceview.ActuatedDeviceView;
import homer.view.javafx.deviceview.AirqualityView;
import homer.view.javafx.deviceview.LockView;
import homer.view.javafx.deviceview.TemperatureChangerView;
import homer.view.javafx.deviceview.ThermometerView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.application.Platform;

/**
 * Main view for {@link Device}s.
 */
public final class JFXDeviceViewer extends VBox implements DeviceViewer {

    private Controller controller;
    private final Map<DeviceId, JFXDeviceView> deviceMap = new LinkedHashMap<>();
    private final Alert invalidDeviceType = new Alert(AlertType.ERROR, "Invalid device type");

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
                final Optional<JFXDeviceView> deviceView;
                if (deviceState instanceof TemperatureChangerState temperatureChangerState) {
                    deviceView = Optional.of(new TemperatureChangerView(deviceId, temperatureChangerState,
                            controller));
                } else if (deviceState instanceof LockState lockState) {
                    deviceView = Optional.of(new LockView(deviceId, controller));
                } else if (deviceState instanceof ThermometerState thermometerState) {
                    deviceView = Optional.of(new ThermometerView(thermometerState));
                } else if (deviceState instanceof AirQualityState airQualityState) {
                    deviceView = Optional.of(new AirqualityView(airQualityState));
                } else if (deviceState instanceof ActuatedDeviceState actuatedDeviceState) {
                    deviceView = Optional.of(new ActuatedDeviceView(actuatedDeviceState));

                } else {
                    invalidDeviceType.showAndWait().filter(r -> r == ButtonType.OK);
                    deviceView = Optional.empty();
                }
                deviceView.ifPresentOrElse(s -> {
                    deviceMap.put(deviceId, s);
                    this.getChildren().add(s);
                }, () -> controller.getDeviceManager().removeDevice(deviceId));
            }
        });
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        Platform.runLater(() -> {
            if (deviceMap.containsKey(deviceId)) {
                final var target = deviceMap.get(deviceId);
                this.getChildren().remove(target);
                deviceMap.remove(deviceId);
            }
        });
    }

    @Override
    public void start(final Controller controller) {
        this.controller = controller;
    }

}
