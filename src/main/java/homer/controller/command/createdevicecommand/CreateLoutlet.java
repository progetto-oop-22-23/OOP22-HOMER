package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.outlets.OutletFactory;

/**
 * Tells the controller to create a {@link homer.model.outlets.Outlet},
 * specifically an L Outlet.
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
