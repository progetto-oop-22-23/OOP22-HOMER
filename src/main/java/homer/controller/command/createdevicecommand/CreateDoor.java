package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.actuator.ActuatorFactory;
import homer.model.door.MechanizedDoor;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link MechanizedDoor}.
 */
public final class CreateDoor implements CreateDeviceCommand {

    private static final String NAME = "Add Door";

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new MechanizedDoor(ActuatorFactory.createSimpleActuator()));
    }

    @Override
    public String getStringRep() {
        return NAME;
    }

}
