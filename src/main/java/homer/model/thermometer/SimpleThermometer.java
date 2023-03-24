package homer.model.thermometer;

import java.util.Objects;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.common.Temperature;

/**
 * Implementation of a {@link Device} which returns the temperature of the
 * environment as it is, without errors or lags.
 */
public final class SimpleThermometer implements Thermometer {

    private static final String DEVICE_TYPE = "Thermometer";
    private final DeviceInfo deviceInfo = new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    private Temperature temperature;

    /**
     * Creates a new {@link SimpleThermometer}.
     * 
     * @param temperature The initial temperature.
     */
    public SimpleThermometer(final Temperature temperature) {
        this.temperature = Objects.requireNonNull(temperature);
    }

    @Override
    public DeviceInfo getInfo() {
        return this.deviceInfo;
    }

    @Override
    public Temperature getState() {
        return this.temperature;
    }

    @Override
    public void setTemperature(final Temperature temperature) {
        this.temperature = Objects.requireNonNull(temperature);
    }

}
