package homer.controller.command;

import homer.api.DeviceId;

/**
 * @param deviceId The device to be disconnnected.
 */
public record DisconnectDevice(DeviceId deviceId) implements Command {

}
