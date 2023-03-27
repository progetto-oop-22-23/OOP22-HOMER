package homer.controller;

import java.util.Set;

import homer.api.DeviceView;
import homer.controller.command.Command;
import homer.core.DiscreteObject;

/**
 * This interface models a domotic controller.
 */
public interface Controller extends DiscreteObject {

    /**
     * Returns a set containing the information of each device to be displayed
     * visually.
     * 
     * @return The devices connected to the controller.
     */
    Set<DeviceView<?>> getDevices();

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
