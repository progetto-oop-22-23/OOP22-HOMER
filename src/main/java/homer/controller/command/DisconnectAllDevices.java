package homer.controller.command;

import homer.controller.Controller;

/**
 * Disconnects all the devices from the controller.
 */
public record DisconnectAllDevices() implements Command {

    @Override
    public void execute(Controller controller) {
        controller.removeAllDevices();
    }

}
