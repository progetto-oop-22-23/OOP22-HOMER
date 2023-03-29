package homer.view.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;
import homer.model.temperaturechangers.TemperatureChangerState;

/**
 * LoggerImpl
 */
public final class LoggerImpl implements Logger {
    private final Map<DeviceId, String> map = new LinkedHashMap<>();
    private OutputStream outputStream;

    /**
     * 
     * @param outputStream
     */
    public LoggerImpl(final OutputStream outputStream) {
        Objects.requireNonNull(outputStream);
        this.outputStream = outputStream;
    }

    @Override
    public void updateDeviceState(final DeviceId deviceId, final DeviceState deviceState) {
        if (!map.containsKey(deviceState)) {
            if (deviceState instanceof TemperatureChangerState) {
                map.put(deviceId, "" + deviceId.toString());
            }
        }
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        map.remove(deviceId);
    }

    @Override
    public void setOutputStream(final OutputStream outputStream) {
        Objects.requireNonNull(outputStream);
        this.outputStream = outputStream;
    }

    @Override
    public void start(final Controller controller) {
        Objects.requireNonNull(outputStream);
    }

    @Override
    public void log(final String string) {
        try {
            this.outputStream.write((string + "\n").getBytes());
        } catch (IOException e) {

        }
    }

}
