package homer.view.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.state.ActuatedDeviceState;
import homer.api.state.LockState;
import homer.api.state.ThermometerState;
import homer.common.bounds.Bounds;
import homer.controller.Controller;
import homer.model.lights.LightState;
import homer.model.outlets.OutletState;
import homer.model.temperaturechangers.TemperatureChangerState;

/**
 * Basic {@link Logger} that can be decorated.
 */
public final class LoggerImpl implements Logger {
    private final Map<DeviceId, String> stringReps = new LinkedHashMap<>();
    private OutputStream outputStream;
    private static final String SEPARATOR = ":";
    private static final String UNDEFINED = "UNDEFINED";

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
            final String stringRep = deviceCreationInfo(deviceState);
            stringReps.put(deviceId, stringRep);
            log("ADD DEVICE");
        } else {
            log("UPDATE DEVICE");
        }
        log(SEPARATOR);
        log(String.join(SEPARATOR, deviceId.toString(), stringReps.get(deviceId)));
        log(SEPARATOR);
        if (deviceState instanceof TemperatureChangerState) {
            log("CURRENT INTENSITY:"  + ((TemperatureChangerState) deviceState)
                .getCurrentIntensity()
                .map(x -> x.toString())
                .orElseGet(() -> UNDEFINED));
        } else if (deviceState instanceof ActuatedDeviceState state) {
            log(Integer.toString(state.getPosition()));
        } else if (deviceState instanceof LockState state) {
            log("LOCKED:" + state.isOn());
        } else if (deviceState instanceof LightState state) {
            log("TURNED ON:" + state.isOn());
        } else if (deviceState instanceof OutletState state) {
            log("POWER:" + state.getPower()
            .map(x -> toString()).orElseGet(() -> UNDEFINED));
        } else if (deviceState instanceof ThermometerState state) {
            log("TEMPERATURE:" + state.getTemperature().getCelsius() + "C");
        }
        log("\n");
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
            System.out.println(e.toString()); // NOPMD if logger fails, we have to report it somewhere.
        }
    }

    private String deviceCreationInfo(final DeviceState deviceState) {
        if (deviceState instanceof TemperatureChangerState state) {
            return "Temperature Changer " + state.getType()
                    .map(x -> "AIR CONDITIONING".equals(x) ? "Air conditioning" : "Heating")
                    .orElseGet(() -> UNDEFINED);
        } else if (deviceState instanceof ActuatedDeviceState state) {
            return "Actuated device"
                    + (state.getPositionBounds()
                            .map(Bounds::toString).orElseGet(() -> UNDEFINED));
        } else if (deviceState instanceof LockState) {
            return "Lock";
        } else if (deviceState instanceof OutletState) {
            return "Outlet";
        } else if (deviceState instanceof ThermometerState) {
            return "Thermometer";
        } else if (deviceState instanceof LightState) {
            return "Light";
        } else {
            throw new IllegalArgumentException();
        }
    }

}
