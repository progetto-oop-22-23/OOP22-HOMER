package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.outlets.OutletFactory;

public class CreateLoutlet implements CreateDeviceCommand {

    @Override
    public void execute(Controller controller) {
        controller.getDeviceManager().addDevice(OutletFactory.lOutlet(0));
    }

    @Override
    public String getStringRep() {
        return "Create Loutlet";
    }

}
