package homer.view.javafx;

import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.controller.Controller;
import homer.controller.command.DisconnectDevice;
import javafx.scene.control.Button;

/**
 * Disconnects the device from the view.
 */
public final class DisconnectDeviceButton extends Button {

    /**
     * 
     * @param info the device's info
     * @param controller the receiving controller
     */
    public DisconnectDeviceButton(final DeviceInfo info, final Controller controller) {
        this.setText("Remove Device");
        this.setOnMouseClicked(e -> controller.receiveCommand(new DisconnectDevice(new DeviceIdImpl()))); // TODO FIX
    }
}
