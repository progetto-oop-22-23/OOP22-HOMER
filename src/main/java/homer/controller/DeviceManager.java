package homer.controller;

import java.util.Map;

import homer.api.Device;
import homer.api.DeviceId;

/**
 * Manages devices and allows {@link homer.controller.command.Command}s to be
 * executed.
 */
public interface DeviceManager extends DeviceManagerObserver {

    /**
     * Adds a new device to the connected devices.
     * 
     * @param device the device to be added.
     */
    void addDevice(Device<?> device);

    /**
     * Returns the currently connected devices.
     * 
     * @return the currently connected devices.
     */
    Map<DeviceId, Device<?>> getDevices();

}
