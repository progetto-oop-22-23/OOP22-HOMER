package homer.controller.command.createdevicecommand;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.controller.Controller;
import homer.model.environment.Environment;
import homer.model.temperaturechangers.AirConditioning;

/** 
 * Tells the controller to create an air conditioning device.
 * @param environment
*/
public record CreateAirConditioning(Environment environment) implements CreateDeviceCommand {
    private static final double HEATING_MAX_DEFAULT_INTENSITY = 10;

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Exposing a reference is intended here")
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new AirConditioning(0, HEATING_MAX_DEFAULT_INTENSITY, environment));
    }

    @Override
    public String getStringRep() {
        return "Create Air conditioning";
    }

}
