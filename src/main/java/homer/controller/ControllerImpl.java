package homer.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import homer.DeviceInfoImpl;
import homer.api.Device;
import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.api.DeviceView;
import homer.model.lights.Light;

/**
 * Controller Implementation.
 */
public final class ControllerImpl implements Controller {

    private Map<DeviceId, Device<?>> devices = new LinkedHashMap<>();

    @Override
    public void connectDevice(String deviceType) {
        DeviceId device = new DeviceIdImpl();
        devices.put(device, new Light(new DeviceInfoImpl(device, deviceType), true));
    }

    @Override
    public void disconnectDevice(DeviceId deviceId) {
        devices.remove(deviceId);
    }

    @Override
    public Set<DeviceView<?>> getDevices() {
        throw new UnsupportedOperationException("Unimplemented method 'getDevices'");
    }

    @Override
    public void signalDevice(DeviceView<?> device) {

    }
    
}
