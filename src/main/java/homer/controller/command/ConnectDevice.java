package homer.controller.command;

import homer.api.DeviceType;

/**
 *
 * @param  deviceType the type of the device that will be connected
 */
public record ConnectDevice(DeviceType deviceType) implements Command {

}
