package homer.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import homer.api.Device;
import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.api.ToggleableDevice;
import homer.controller.command.createdevicecommand.CreateAirConditioning;
import homer.controller.command.createdevicecommand.CreateDeviceCommand;
import homer.controller.command.createdevicecommand.CreateHeating;

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

    @Override
    public void addDevice(Device<?> device) {
        this.deviceMap.put(new DeviceIdImpl(), device);
    }

    @Override
    public Set<CreateDeviceCommand> getValidCreateDeviceCommands() {
        return Set.of(new CreateAirConditioning(), new CreateHeating());
    }


}
