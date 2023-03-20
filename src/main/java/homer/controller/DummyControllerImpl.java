package homer.controller;

import java.util.Set;

import homer.api.Controller;
import homer.api.DeviceId;
import homer.api.DeviceView;

public final class DummyControllerImpl implements Controller {

    @Override
    public DeviceId connectDevice(String deviceType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connectDevice'");
    }

    @Override
    public void disconnectDevice(DeviceId deviceId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'disconnectDevice'");
    }

    @Override
    public Set<DeviceView<?>> getDevices() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDevices'");
    }

    @Override
    public void signalDevice(DeviceView<?> device) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signalDevice'");
    }

    @Override
    public Set<String> getConnectableDeviceTypes() {
        return Set.of("Device 1", "Device 2");
    }
    
}
