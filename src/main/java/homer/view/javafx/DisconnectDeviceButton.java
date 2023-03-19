package homer.view.javafx;

import homer.api.Controller;
import homer.api.DeviceInfo;
import javafx.scene.control.Button;

public final class DisconnectDeviceButton extends Button {
    private final DeviceInfo info;
    private final Controller controller;

    public DisconnectDeviceButton(final DeviceInfo info, final Controller controller) {
        this.setText("Remove Device");
        this.info = info;
        this.controller = controller;
        this.setOnMouseClicked(e -> controller.disconnectDevice(info.getID()));
    }
}
