package homer.model.thermometer;

import java.time.Duration;
import java.util.Objects;

import homer.api.state.ThermometerState;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.environment.Environment;

/**
 * Implementation of a {@link Device} which returns the temperature of the
 * environment as it is, without errors or lags.
 */
public final class SimpleThermometer implements Thermometer {

    private final Environment environment;
    private Temperature temperature;

    /**
     * Creates a new {@link SimpleThermometer}.
     * 
     * @param environment the environment in which the thermometer is placed.
     */
    public SimpleThermometer(final Environment environment) {
        this.environment = Objects.requireNonNull(environment);
        senseTemperature();
    }

    @Override
    public ThermometerState getState() {
        return new ThermometerState(this.temperature);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        senseTemperature();
    }

    private void senseTemperature() {
        this.temperature = TemperatureFactory.fromKelvin(this.environment.getTemperature().getKelvin());
    }

}
