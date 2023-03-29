package homer.controller.command.createdevicecommand;

import homer.controller.command.Command;

/**
 * These commands tell the {@link Controller} to create a new {@link Device}.
 */
public interface CreateDeviceCommand extends Command {

    /**
     * 
     * Returns the string representation of the command.
     * 
     * @return the string representation of the command.
     */
    String getStringRep();
}
