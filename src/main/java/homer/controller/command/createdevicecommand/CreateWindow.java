package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.actuator.ActuatorFactory;
import homer.model.window.MechanizedWindow;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link MechanizedWindow}.
 */
public final class CreateWindow implements CreateDeviceCommand {

    private static final String NAME = "Add Window";

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new MechanizedWindow(ActuatorFactory.createSimpleActuator()));
    }

    @Override
    public String getStringRep() {
        return NAME;
    }

}
