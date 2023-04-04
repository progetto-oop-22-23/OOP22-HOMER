package homer.controller.command.createdevicecommand;

import homer.controller.command.Command;

/**
 * These commands tell the {@link homer.controller.Controller } to create a new {@link homer.api.Device}.
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
