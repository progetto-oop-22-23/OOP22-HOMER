package homer.controller.command;

import homer.api.DeviceId;

/**
 * @param deviceId id of the device to be toggled
 */
public record ToggleDevice(DeviceId deviceId) implements Command {

}
