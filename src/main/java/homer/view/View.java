package homer.view;

import homer.api.DeviceId;
import homer.api.DeviceState;

/**
 * View that supports adding, removing and updating state of the devices.
 */
public interface View {
    /**
     * Updates the selected device's state on the view, and creates a new view if
     * it's not present yet.
     * 
     * @param deviceId        the id of the device to be updated
     * @param serializedState the device's new state
     */
    void updateDeviceState(DeviceId deviceId, DeviceState deviceState);

    /**
     * 
     * @param deviceId the device to be removed
     */
    void removeDevice(DeviceId deviceId);
    
}
