package homer.controller;

import homer.controller.command.Command;
import homer.core.DiscreteObject;

/**
 * This interface models a domotic controller.
 */
public interface Controller extends DiscreteObject {

    /**
     * Register the user sending the {@link Command} command.
     * 
     * @param command
     */
    void receiveCommand(Command command);

    /**
     * 
     * Returns the DeviceManager.
     * 
     * @return The DeviceManager.
     */
    DeviceManager getDeviceManager();

    /**
     * Returns the ViewManager.
     * 
     * @return The ViewManager.
     */
    ViewManager getViewManager();
}
