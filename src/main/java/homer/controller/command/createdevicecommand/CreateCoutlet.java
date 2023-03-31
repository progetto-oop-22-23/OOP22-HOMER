package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.outlets.OutletFactory;

public class CreateCoutlet implements CreateDeviceCommand {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(OutletFactory.cOutlet(0.0));
    }

    @Override
    public String getStringRep() {
        return "Create Coutlet";
    }

}
