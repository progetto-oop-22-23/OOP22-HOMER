package homer.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import homer.api.Device;
import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.api.ToggleableDevice;
import homer.model.outlets.OutletFactory;

/**
 * DeviceManager implementation.
 */
public final class DeviceManagerImpl implements DeviceManager {
    private final Map<DeviceId, Device<?>> deviceMap = new LinkedHashMap<>();


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

    private void addDevice(final Device<?> device) {
        Objects.requireNonNull(device);
        final DeviceId id = device.getInfo().getID();
        deviceMap.put(id, device);
    }

    @Override
    public void createDevice(final DeviceType deviceType) {
        switch (deviceType) {
            case L_OUTLET:
                this.addDevice(OutletFactory.lOutlet(null, 0));
            case C_OUTLET:
                this.addDevice(OutletFactory.lOutlet(null, 0));
            case WINDOW:
                this.addDevice();
        }
    }

}
