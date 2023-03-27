package homer.view.logger;


import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import homer.api.DeviceId;
import homer.api.DeviceState;

public class LoggerImpl implements Logger{
    private final Map<DeviceId, String> map = new LinkedHashMap<>();

    @Override
    public void updateDeviceState(DeviceId deviceId, DeviceState deviceState) {
        if (map.containsKey(deviceId)) {
            // print what's updated.
        } else {
            map.put(deviceId, "Some kind of string rep that we only get on first creation of the object");
            // print something on first update.
        }
    }

    @Override
    public void removeDevice(DeviceId deviceId) {
        map.remove(deviceId);
    }

    @Override
    public void setOutputStream(OutputStream outputStream) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setOutputStream'");
    }
    
}
