package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.temperaturechangers.AirConditioning;

public class CreateAirConditioning implements CreateDeviceCommand {

    @Override
    public void execute(Controller controller) {
        controller.getDeviceManager().addDevice(new AirConditioning(0, 0, null));
    }

    @Override
    public String getStringRep() {
        return "Create Air conditioning";
    }
    
}
