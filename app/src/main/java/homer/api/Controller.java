package homer.api;

import java.util.Set;

public interface Controller {

    void connectDevice(Device<?> device);

    Set<DeviceView<?>> getDevices();

    void updateDevice(DeviceView<?> device);

}
