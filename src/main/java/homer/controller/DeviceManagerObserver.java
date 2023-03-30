package homer.controller;

import java.util.Set;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.command.createdevicecommand.CreateDeviceCommand;

/**
 * Interface of the device manager to be called from the view.
 */
public interface DeviceManagerObserver {

    /**
     * Removes a device.
     * 
     * @param deviceId the device to be removed.
     */
    void removeDevice(DeviceId deviceId);

    /**
     * Removes all Devices.
     */
    void removeAllDevices();

    /**
     * Returns the set of {@link CreateDeviceCommand} commands that can be displayed
     * (and sent back to the controller) to the view.
     * 
     * @return Set of valid commands.
     */
    Set<CreateDeviceCommand> getValidCreateDeviceCommands();

    /**
     * Receives an update command from the view.
     * 
     * @param deviceId the id of the device to update.
     * @param state    the updated state.
     */
    void updateDeviceState(DeviceId deviceId, DeviceState state);

    /**
     * Toggles a device whose state is toggleable.
     * 
     * @param deviceId the device to be toggled.
     */
    void toggleDevice(DeviceId deviceId);

}
