package homer.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import homer.api.Device;
import homer.api.DeviceId;
import homer.api.ToggleableDevice;

/**
 * DeviceManager implementation.
 */
public final class DeviceManagerImpl implements DeviceManager {
    private final Map<DeviceId, Device<?>> deviceMap = new LinkedHashMap<>();

    @Override
    public void createDevice(final String deviceType) {

    }

    @Override
    public void removeAllDevices() {
        this.deviceMap.clear();
    }

    @Override
    public boolean isDeviceConnected(final DeviceId deviceId) {
        return deviceMap.containsKey(deviceId);
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        if (this.isDeviceConnected(deviceId)) {
            deviceMap.remove(deviceId);
        }
    }

    @Override
    public void toggleDevice(final DeviceId deviceId) {
        final Device<?> targetDevice = deviceMap.get(deviceId);
        if (targetDevice instanceof ToggleableDevice) {
            final ToggleableDevice<?> toggleableDevice = (ToggleableDevice<?>) targetDevice;
            toggleableDevice.toggle();
        } else {
            throw new IllegalArgumentException("Device is not toggleable");
        }

    }

}
