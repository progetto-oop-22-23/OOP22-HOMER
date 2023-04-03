package homer.controller.command.createdevicecommand;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.controller.Controller;
import homer.model.airquality.AirqualitySensor;
import homer.model.environment.Environment;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link AiqualitySensor}.
 * @param environment the environment which is going to be observed by the sensor.
 */
public record CreateAirQualitySensor(Environment environment) implements CreateDeviceCommand {

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Reference is needed in order for this to work")
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new AirqualitySensor(this.environment));
    }

    @Override
    public String getStringRep() {
        return "Add Air quality sensor";
    }

}
