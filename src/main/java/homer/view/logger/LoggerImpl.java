package homer.view.logger;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;

/**
 * {@link Logger} implementation.
 */
public final class LoggerImpl implements Logger {
    private final Map<DeviceId, String> map = new LinkedHashMap<>();
    private Controller controller;
    private OutputStream outputStream;

    public LoggerImpl(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void updateDeviceState(final DeviceId deviceId, final DeviceState deviceState) {
        if (map.containsKey(deviceId)) {
            // print what's updated.
        } else {
            map.put(deviceId, "Some kind of string rep that we only get on first creation of the object");
            // print something on first update.
        }
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        map.remove(deviceId);
    }

    @Override
    public void setOutputStream(final OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void start(final Controller controller) {
        this.controller = controller;
    }

}
