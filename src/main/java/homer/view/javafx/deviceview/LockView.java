package homer.view.javafx.deviceview;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.state.LockState;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.view.javafx.ButtonComponent;
import homer.view.javafx.JFXDeviceView;
import javafx.scene.control.Label;

/**
 * Implements view for {@link Lock} {@link Device}s.
 */
public final class LockView extends JFXDeviceView {
    private ButtonComponent<Boolean> button; // NOPMD

    /**
     * 
     * @param deviceId the device's id
     * @param controller the controller that receives the update
     * @param deviceState the device's state.
     */
    public LockView(final DeviceId deviceId, final Controller controller, final LockState deviceState) {
        this.getChildren().add(new Label("Lock:"));
        button = new ButtonComponent<>(
                s -> controller.receiveCommand(new UpdateDeviceState(deviceId, new LockState(!s))),
                "Lock:", false);
        this.setState(deviceState);
        this.getChildren().add(button);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof LockState lockState) {
            this.button.setState(lockState.isOn());
        }
    }

}
