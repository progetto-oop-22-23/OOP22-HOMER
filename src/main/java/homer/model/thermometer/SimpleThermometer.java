package homer.model.thermometer;

import java.util.Objects;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.common.Temperature;
import homer.model.environment.Environment;

/**
 * Implementation of a {@link Device} which returns the temperature of the
 * environment as it is, without errors or lags.
 */
public final class SimpleThermometer implements Thermometer {

    private static final String DEVICE_TYPE = "Thermometer";
    private final DeviceInfo deviceInfo = new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    private final Environment environment;

    /**
     * Creates a new {@link SimpleThermometer}.
     * 
     * @param environment The environment in which the thermometer is placed.
     */
    public SimpleThermometer(final Environment environment) {
        this.environment = Objects.requireNonNull(environment);
    }

    @Override
    public DeviceInfo getInfo() {
        return this.deviceInfo;
    }

    @Override
    public Temperature getState() {
        return this.environment.getTemperature();
    }

}
