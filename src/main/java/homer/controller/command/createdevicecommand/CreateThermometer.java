package homer.controller.command.createdevicecommand;

import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.controller.Controller;
import homer.model.environment.Environment;
import homer.model.thermometer.SimpleThermometer;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link SimpleThermometer}.
 */
public final class CreateThermometer implements CreateDeviceCommand {

    private static final String NAME = "Add Thermometer";
    private final Environment environment;

    /**
     * Creates a new {@link CreateThermometer}.
     * 
     * @param environment the environment in which to create the thermometer.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "This is intentional for the creation of the thermometer")
    public CreateThermometer(final Environment environment) {
        this.environment = Objects.requireNonNull(environment);
    }

    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Exposing a reference is intended here")
    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new SimpleThermometer(this.environment));
    }

    @Override
    public String getStringRep() {
        return NAME;
    }

}
