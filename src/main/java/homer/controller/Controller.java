package homer.controller;

import java.util.Set;

import homer.api.DeviceId;
import homer.api.DeviceView;
import homer.controller.command.Command;

/**
 * This interface models a domotic controller.
 */
public interface Controller {

    /**
     * Creates and Connects a new device to the controller.
     * @param deviceType
     * 
     */
    void connectDevice(String deviceType);

    /**
     * Disconnects the device from the controller.
     * 
     * @param deviceId The ID of the device to be disconnected.
     */
    void disconnectDevice(DeviceId deviceId);

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

}
