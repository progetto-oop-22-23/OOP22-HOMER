package homer.view.javafx;

import homer.api.DeviceIdImpl;
import homer.controller.Controller;
import homer.controller.command.DisconnectDevice;
import javafx.scene.control.Button;

/**
 * Disconnects the device from the view.
 */
public final class DisconnectDeviceButton extends Button {
    private final Controller controller;

    public DisconnectDeviceButton(final Controller controller) {
        this.setText("Remove Device");
        this.controller = controller;
        this.setOnMouseClicked(e -> controller.receiveCommand(new DisconnectDevice(new DeviceIdImpl()))); // TODO FIX
    }
}
