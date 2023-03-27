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
     * 
     * @param deviceType
     */
    void createDevice(String deviceType);

    /**
     * Removes all Devices.
     */
    void removeAllDevices();

    /**
     * Returns whether the device is connected or not.
     * 
     * @param deviceId the device we want to know the state of.
     * @return Whether the device is connected or not.
     */
    boolean isDeviceConnected(DeviceId deviceId);

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
     * @param device the device to be added.
     */
    void addDevice(Device<?> device);

    public Set<CreateDeviceCommand> getValidCreateDeviceCommands();

    public void UpdateDeviceState(DeviceId deviceId, DeviceState state);
}
