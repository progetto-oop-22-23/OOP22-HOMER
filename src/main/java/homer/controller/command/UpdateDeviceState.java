package homer.controller.command;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;

public record UpdateDeviceState(DeviceId id, DeviceState deviceState) implements Command {

    @Override
    public final void execute(final Controller controller) {
        controller.getDeviceManager().UpdateDeviceState(id, deviceState);
    }
    
}