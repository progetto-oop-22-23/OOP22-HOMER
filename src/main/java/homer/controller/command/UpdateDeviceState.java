package homer.controller.command;

import homer.api.DeviceId;

/**
 * @param deviceId The device's id
 * @param state The device's new state
 */
public record UpdateDeviceState(DeviceId deviceId, Object state) implements Command {

}
