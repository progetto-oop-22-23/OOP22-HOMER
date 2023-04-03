package homer.controller.command.createdevicecommand;

import java.util.Objects;

import homer.controller.Controller;
import homer.model.airquality.AirqualitySensor;
import homer.model.environment.Environment;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link AiqualitySensor}.
 */
public final class CreateAirQualitySensor implements CreateDeviceCommand {

    private final Environment environment;

    /**
     * 
     * @param environment
     */
    public CreateAirQualitySensor(final Environment environment) {
        this.environment = Objects.requireNonNull(environment);
    }

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new AirqualitySensor(this.environment));
    }

    @Override
    public String getStringRep() {
        return "Add Air quality sensor";
    }

}
