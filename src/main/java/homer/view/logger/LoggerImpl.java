package homer.view.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.state.ActuatedDeviceState;
import homer.api.state.OnOffState;
import homer.api.state.ThermometerState;
import homer.controller.Controller;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletState;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.model.temperaturechangers.TemperatureChangerType;
import homer.model.thermometer.Thermometer;

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
            String stringRep = "";
            if (deviceState instanceof TemperatureChangerState) {
                final var state = (TemperatureChangerState) deviceState;
                if (state.getType().get().equals(TemperatureChangerType.AIRCONDITIONING)) {
                    stringRep = "Air conditioning";
                } else {
                    stringRep = "Heating";
                }
            } else if (deviceState instanceof ActuatedDeviceState) {
                final var state = (ActuatedDeviceState) deviceState;
                stringRep = "Actuated device";
                if (state.getPositionBounds().isPresent()) {
                    stringRep += state.getPositionBounds().get().toString();
                }
            } else if (deviceState instanceof OnOffState) {

            } else if (deviceState instanceof OutletState) {
            } else if (deviceState instanceof ThermometerState) {
                stringRep = "Thermometer";
            }
            stringReps.put(deviceId, stringRep);
            log("ADD DEVICE:" + deviceId.toString() + ":" + stringRep);

        } else {
            log("UPDATE DEVICE:" + deviceId.toString() + ":" + stringReps.get(deviceId));
        }
        if (deviceState instanceof TemperatureChangerState) {
            final var state = (TemperatureChangerState) deviceState;
            log(state.getCurrentIntensity().get().toString());
        } else if (deviceState instanceof ActuatedDeviceState) {
            final var state = (ActuatedDeviceState) deviceState;
            log(Integer.toString(state.getPosition()));
        } else if (deviceState instanceof OnOffState) {
            log("TURNED ON:" + ((OnOffState) deviceState).isOn());
        } else if (deviceState instanceof OutletState) {
            log("POWER:" + ((OutletState) deviceState).getPower().get());
        } else if (deviceState instanceof ThermometerState) {
            log("TEMPERATURE:" + ((ThermometerState) deviceState).getTemperature().getCelsius() + "C");
        }
    }

    @Override
    public void removeDevice(final DeviceId deviceId) {
        log("REMOVE DEVICE:" + deviceId.toString() + ":" + stringReps.get(deviceId) + "\n");
        stringReps.remove(deviceId);
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
