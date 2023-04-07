package homer.controller.command;

import homer.api.DeviceId;
import homer.controller.Controller;

/**
 * @param deviceId The device to be disconnnected.
 */
public record DisconnectDevice(DeviceId deviceId) implements Command {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().removeDevice(deviceId);
    }

}
