package homer.controller;

import java.util.Set;

import homer.api.Device;
import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.command.createdevicecommand.CreateDeviceCommand;

/**
 * Manages devices and allows {@link Command}s to be
 * executed.
 */
public interface DeviceManager {

    /**
     * Removes all Devices.
     */
    void removeAllDevices();

    /**
     * 
     * @param deviceId the device to be removed.
     */
    void removeDevice(DeviceId deviceId);

    /**
     * 
     * @param deviceId the device to be toggled.
     */
    void toggleDevice(DeviceId deviceId);

    /**
     * Adds a new device to the connected devices.
     * 
     * @param device the device to be added.
     */
    void addDevice(Device<?> device);

    /**
     * Returns the set of {@link CreateDeviceCommand} commands that can be displayed
     * (and sent back to the controller) to the view
     * 
     * @return Set of valid commands
     */
    public Set<CreateDeviceCommand> getValidCreateDeviceCommands();

    public void UpdateDeviceState(DeviceId deviceId, DeviceState state);
}
