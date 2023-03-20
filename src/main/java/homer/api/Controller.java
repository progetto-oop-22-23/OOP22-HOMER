package homer.api;

import java.util.Set;

/**
 * This interface models a domotic controller.
 */
public interface Controller {

    /**
     * Connects a new device to the controller.
     * 
     * @param device The device to connect.
     */
    DeviceId connectDevice(String deviceType);

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
     * Command an update to the given device.
     * 
     * @param device The device to command.
     */
    void signalDevice(DeviceView<?> device);

    /**
     * 
     * @return all the types of devices that can be connected.
     */
    Set<String> getConnectableDeviceTypes();

}
