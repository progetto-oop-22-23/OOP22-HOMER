package homer.model.thermometer;

import java.util.Objects;

import homer.DeviceInfoImpl;
import homer.api.Device;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.common.Temperature;
import homer.model.environment.Environment;

/**
 * Implementation of a {@link Device} which returns the temperature of the
 * environment as it is, without errors or lags.
 */
public final class SimpleThermometer implements Device<Temperature> {

    private static final String DEVICE_TYPE = "Thermometer";
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
        return new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    }

    @Override
    public Temperature getState() {
        return this.environment.getTemperature();
    }

}
