package homer.view.javafx;

import homer.api.DeviceId;
import homer.controller.Controller;
import homer.controller.command.DisconnectDevice;
import javafx.scene.control.Button;

/**
 * Disconnects the device from the view on click.
 */
public final class DisconnectDeviceButton extends Button {

    /**
     * 
     * @param controller
     * @param deviceId
     */
    public DisconnectDeviceButton(final Controller controller, final DeviceId deviceId) {
        this.setText("Remove Device");
        this.setOnMouseClicked(e -> controller.receiveCommand(new DisconnectDevice(deviceId)));
    }
}
