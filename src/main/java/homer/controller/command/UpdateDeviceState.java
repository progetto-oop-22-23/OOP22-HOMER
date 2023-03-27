package homer.controller.command;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;

public record UpdateDeviceState(DeviceId id, DeviceState deviceState) implements Command {

    @Override
    public void execute(Controller controller) {
        controller.getDeviceManager().UpdateDeviceState(id, deviceState);
    }
    
}