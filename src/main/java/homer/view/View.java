package homer.view;

import homer.api.DeviceId;

/**
 * View that supports adding, removing and updating state of the devices.
 */
public interface View {
    /**
     * Updates the selected device's state on the view
     * @param deviceId        the id of the device to be updated
     * @param serializedState the device's new state
     */
    void updateDeviceState(DeviceId deviceId, String serializedState);

    /**
     * 
     * @param deviceId the device to be removed
     */
    void removeDevice(DeviceId deviceId);

    /**
     * Adds a new device to the view
     * @param deviceId the device's id
     * @param deviceType the device's type
     */
    void addDevice(DeviceId deviceId, String deviceType);
}
