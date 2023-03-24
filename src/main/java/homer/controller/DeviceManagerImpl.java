package homer.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import homer.api.Device;
import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.api.ToggleableDevice;
import homer.model.environment.Environment;
import homer.model.outlets.OutletFactory;
import homer.model.temperaturechangers.Heating;

/**
 * DeviceManager implementation.
 */
public final class DeviceManagerImpl implements DeviceManager {
    private final Map<DeviceId, Device<?>> deviceMap = new LinkedHashMap<>();
    private Environment environment;

    public DeviceManagerImpl(Environment environment) {
        this.environment = environment;
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

    private void addDevice(final Device<?> device) {
        Objects.requireNonNull(device);
        final DeviceId id = new DeviceIdImpl();
        deviceMap.put(id, device);
    }

    @Override
    public void createDevice(final UserSelectableDeviceType deviceType) {
        switch (deviceType) {
            case L_OUTLET:
                this.addDevice(OutletFactory.lOutlet(0));
            case C_OUTLET:
                this.addDevice(OutletFactory.lOutlet(0));
            case HEATING:
                this.addDevice(new Heating(0, 10, this.environment));
            case AIR_CONDITIONING:
                this.addDevice(new Heating(0, 10, this.environment));
            default:
                throw new IllegalStateException();
        }
    }

    @Override
    public Set<UserSelectableDeviceType> getSelectableDeviceTypes() {
        return Set.of(UserSelectableDeviceType.AIR_CONDITIONING, UserSelectableDeviceType.C_OUTLET);
    }


}
