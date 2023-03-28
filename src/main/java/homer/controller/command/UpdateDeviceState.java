package homer.controller.command;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;

/**
 * Signals to the {@link Controller} the change of state of a device.
 * If not present, the controller will create a new device.
 * 
 * @param id          the device's id
 * @param deviceState the device's state
 */
public record UpdateDeviceState(DeviceId id, DeviceState deviceState) implements Command {

    @Override
    public final void execute(final Controller controller) {
        controller.getDeviceManager().UpdateDeviceState(id, deviceState);
    }

}
