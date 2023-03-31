package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.outlets.OutletFactory;

/**
 * Adds a {@link Outlet} with initial consumption 0 to the controller..
 */
public record CreateLoutlet() implements CreateDeviceCommand {

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(OutletFactory.lOutlet(0));
    }

    @Override
    public String getStringRep() {
        return "Create Loutlet";
    }

}
