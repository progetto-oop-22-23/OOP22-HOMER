package homer.view.logger;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.state.ActuatedDeviceState;
import homer.api.state.LockState;
import homer.api.state.ThermometerState;
import homer.controller.Controller;
import homer.model.airquality.AirQualityState;
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
    private static final Charset STANDARD_CHARSET = Charset.defaultCharset();

    /**
     * 
     * @param outputStream
     */
    public LoggerImpl(final OutputStream outputStream) {
        Objects.requireNonNull(outputStream);
        this.outputStream = outputStream;
        log("LOGGER STARTED\n");
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
        if (deviceState instanceof TemperatureChangerState state) {
            log("CURRENT INTENSITY:" + state
                    .getCurrentIntensity()
                    .map(Object::toString)
                    .orElse(UNDEFINED));
        } else if (deviceState instanceof ActuatedDeviceState state) {
            log(Integer.toString(state.getPosition()));
        } else if (deviceState instanceof LockState state) {
            log("LOCKED:" + state.isOn());
        } else if (deviceState instanceof LightState state) {
            log("TURNED ON:" + state.isOn());
        } else if (deviceState instanceof OutletState state) {
            log("POWER:" + state.getPower()
                    .map(x -> toString()).orElse(UNDEFINED));
        } else if (deviceState instanceof ThermometerState state) {
            log("TEMPERATURE:" + state.getTemperature().getCelsius() + "C");
        } else if (deviceState instanceof AirQualityState state) {
            log("AIR QUALITY:" + state.toString());
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
            this.outputStream.write(string.getBytes(STANDARD_CHARSET));
        } catch (IOException e) {
            System.out.println(e.toString()); // NOPMD if logger fails, we have to report it somewhere.
        }
    }

    private String deviceCreationInfo(final DeviceState deviceState) {
        if (deviceState instanceof TemperatureChangerState state) {
            return "Temperature Changer " + state.getType()
                    .map(x -> TemperatureChangerState.AIRCONDITIONING.equals(x) ? "Air conditioning" : "Heating")
                    .orElse(UNDEFINED);
        } else if (deviceState instanceof ActuatedDeviceState state) {
            return state.getType().orElse(UNDEFINED)
                    + " " + (state.getPositionBounds()
                            .map(x -> "lower: " + x.getLowerBound() + " " + "upper: " + x.getUpperBound())
                            .orElse(""))
                    + " current:" + state.getPosition();
        } else if (deviceState instanceof LockState) {
            return "Lock";
        } else if (deviceState instanceof OutletState) {
            return "Outlet";
        } else if (deviceState instanceof ThermometerState) {
            return "Thermometer";
        } else if (deviceState instanceof LightState) {
            return "Light";
        } else if (deviceState instanceof AirQualityState) {
            return "Air quality";
        } else {
            return "ILLEGAL TYPE";
        }
    }

}
