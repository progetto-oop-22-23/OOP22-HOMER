package homer.controller.command;

import homer.controller.Controller;
import homer.controller.DeviceType;

/**
 *
 * @param  deviceType the type of the device that will be connected
 */
public record ConnectDevice(DeviceType deviceType) implements Command {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().createDevice(deviceType);
    }

}
