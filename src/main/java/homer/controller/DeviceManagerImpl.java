package homer.controller;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import homer.api.AdjustableDevice;
import homer.api.Device;
import homer.api.DeviceId;
import homer.api.ToggleableDevice;

public final class DeviceManagerImpl implements DeviceManager {
    private final Map<DeviceId, Device<?>> deviceMap = new LinkedHashMap<>();

    @Override
    public void createDevice(String deviceType) {

    }

    @Override
    public void removeAllDevices() {
        this.deviceMap.clear();
    }

    @Override
    public boolean isDeviceConnected(DeviceId deviceId) {
        return deviceMap.containsKey(deviceId);
    }

    @Override
    public void removeDevice(DeviceId deviceId) {
        if (this.isDeviceConnected(deviceId)) {
            deviceMap.remove(deviceId);
        }
    }

    @Override
    public void updateDeviceState(DeviceId deviceId, Object state) {
        final Device<?> targetDevice = deviceMap.get(deviceId);
        if (targetDevice instanceof ToggleableDevice) {
            if (state instanceof Boolean) {
                final ToggleableDevice<?> toggleableDevice = (ToggleableDevice<?>) targetDevice;
                if (!((Boolean) state).equals(toggleableDevice.isToggled())) {
                    toggleableDevice.toggle();
                }
            }
        } else if (targetDevice instanceof AdjustableDevice) {
            final AdjustableDevice<?> adjustableDevice = (AdjustableDevice<?>) targetDevice;
            Type superclassType = adjustableDevice.getClass().getGenericSuperclass();
            Type actualtype =  ((ParameterizedType) superclassType).getActualTypeArguments()[0];
            if (actualtype.equals(state.getClass())) {
                adjustableDevice.setState((actualType) state); // TODO fix
            }
        }

    }

}
