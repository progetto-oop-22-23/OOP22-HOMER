package homer.controller.command;

/**
 *
 * @param  deviceType the type of the device that will be connected
 */
public record ConnectDevice(String deviceType) implements Command {

}
