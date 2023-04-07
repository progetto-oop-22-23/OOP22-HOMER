package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.actuator.ActuatorFactory;
import homer.model.blinds.MechanizedBlinds;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link MechanizedBlinds}.
 */
public final class CreateBlinds implements CreateDeviceCommand {

    private static final String NAME = "Add Blinds";

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new MechanizedBlinds(ActuatorFactory.createSimpleActuator()));
    }

    @Override
    public String getStringRep() {
        return NAME;
    }

}
