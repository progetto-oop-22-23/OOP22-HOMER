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
import homer.model.temperaturechangers.TemperatureChangerType;

/**
 * LoggerImpl
 */
public final class LoggerImpl implements Logger {
    private final Map<DeviceId, String> stringReps = new LinkedHashMap<>();
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
        if (!stringReps.containsKey(deviceId)) {
            log("ADD DEVICE:");
            if (deviceState instanceof TemperatureChangerState) {
                final var state = (TemperatureChangerState)  deviceState;
                final String typeStringRep;
                if (state.getType().get().equals(TemperatureChangerType.AIRCONDITIONING)) {
                    typeStringRep = "Air conditioning";
                }
                else {
                    typeStringRep = "Heating";
                }
                stringReps.put(deviceId, "" + deviceId.toString() + ":" + typeStringRep);;
            }
        }
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        stringReps.remove(deviceId);
        log("REMOVE DEVICE:");
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
            this.outputStream.write(string.getBytes());
        } catch (IOException e) {

        }
    }

}
