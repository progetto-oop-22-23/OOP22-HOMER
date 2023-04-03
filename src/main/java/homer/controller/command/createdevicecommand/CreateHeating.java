package homer.controller.command.createdevicecommand;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.controller.Controller;
import homer.model.environment.Environment;
import homer.model.temperaturechangers.Heating;

/**
 * @param environment the environment.
 */
public record CreateHeating(Environment environment) implements CreateDeviceCommand {
    private static final double HEATING_MAX_DEFAULT_INTENSITY = 10;

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Reference is needed in order for this to work")
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new Heating(0, HEATING_MAX_DEFAULT_INTENSITY, environment));
    }

    @Override
    public String getStringRep() {
        return "Create heating";
    }

}
