package homer.controller;

import homer.api.Device;

/**
 * Manages devices and allows {@link Command}s to be
 * executed.
 */
public interface DeviceManager extends DeviceManagerObserver {

    /**
     * Adds a new device to the connected devices.
     * 
     * @param device the device to be added.
     */
    void addDevice(Device<?> device);

}
