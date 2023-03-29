package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.environment.Environment;
import homer.model.temperaturechangers.AirConditioning;

/** 
 * Tells the controller to create an air conditioning device.
 * @param environment
*/
public record CreateAirConditioning(Environment environment) implements CreateDeviceCommand {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new AirConditioning(0, 0, environment));
    }

    @Override
    public String getStringRep() {
        return "Create Air conditioning";
    }

}
