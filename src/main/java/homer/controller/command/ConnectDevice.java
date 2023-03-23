package homer.controller.command;

import homer.controller.Controller;

/**
 *
 * @param  deviceType the type of the device that will be connected
 */
public record ConnectDevice(String deviceType) implements Command {

    @Override
    public void execute(Controller controller) {
    }

}
