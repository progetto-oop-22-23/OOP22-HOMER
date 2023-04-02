package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.airquality.AirqualitySensor;
import homer.model.environment.Environment;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link AiqualitySensor}.
 * @param environment the environment which is going to be observed by the sensor.
 */
public record CreateAirQualitySensor(Environment environment) implements CreateDeviceCommand {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new AirqualitySensor(this.environment));
    }

    @Override
    public String getStringRep() {
        return "Add Air quality sensor";
    }

}
