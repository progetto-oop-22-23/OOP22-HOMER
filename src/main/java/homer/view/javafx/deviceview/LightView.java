package homer.view.javafx.deviceview;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.model.lights.LightState;
import homer.view.javafx.JFXDeviceView;
import javafx.scene.control.Label;

public class LightView extends JFXDeviceView {

    private final ToggleableView toggleableView;

    public LightView(final DeviceId deviceId, final Controller controller) {
        toggleableView = new ToggleableView("ON", "OFF",
                s -> controller.receiveCommand(new UpdateDeviceState(deviceId, new LightState(s))));
        final Label label = new Label("Light");
        this.getChildren().addAll(label, toggleableView);
    }

    @Override
    public void setState(DeviceState state) {
        if (state instanceof LightState lightState) {
            toggleableView.setState(lightState.isOn());
        }
    }

}
