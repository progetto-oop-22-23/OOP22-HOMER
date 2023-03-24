package homer.controller.command;

import homer.api.DeviceId;
import homer.controller.Controller;

/**
 * @param deviceId id of the device to be toggled
 */
public record ToggleDevice(DeviceId deviceId) implements Command {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager();
    }

}
