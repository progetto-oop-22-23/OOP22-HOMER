package homer.controller;

import java.util.Set;

import homer.api.DeviceId;

/**
 * Manages devices and allows {@link Command}s to be 
 * executed.
 */
public interface DeviceManager {
    /**
     * 
     * @param deviceType
     */
    void createDevice(UserSelectableDeviceType deviceType);

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

    Set<UserSelectableDeviceType> getSelectableDeviceTypes();
}
