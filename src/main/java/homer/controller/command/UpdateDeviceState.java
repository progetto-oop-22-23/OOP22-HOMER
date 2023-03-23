package homer.controller.command;

import homer.api.DeviceId;
import homer.controller.Controller;

/**
 * @param deviceId The device's id
 * @param state The device's new state
 */
public record UpdateDeviceState(DeviceId deviceId, Object state) implements Command {

    @Override
    public void execute(Controller controller) {
        if (controller.getDeviceManager().isDeviceConnected(deviceId)) {
            controller.getDeviceManager().updateDeviceState(deviceId, controller); 
        }
    }

}
