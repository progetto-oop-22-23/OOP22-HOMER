package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.lights.Light;

/**
 * Command to create a new Light device.
 */
public final class CreateLight implements CreateDeviceCommand {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new Light(true));
    }

    @Override
    public String getStringRep() {
        return "Add Light";
    }

}
