package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.lights.Light;

public class CreateLight implements CreateDeviceCommand {

    @Override
    public void execute(Controller controller) {
       controller.getDeviceManager().addDevice(new Light(true));
    }

    @Override
    public String getStringRep() {
        return "Create Light";
    }
    
}
