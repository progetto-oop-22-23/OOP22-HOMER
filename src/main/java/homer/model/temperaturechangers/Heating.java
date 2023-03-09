package homer.model.temperaturechangers;

import java.time.Duration;

import homer.common.Temperature;
import homer.common.TemperatureFactory;
import homer.model.environment.Environment;

/**
 * Models a heating device.
 */
public final class Heating extends AbstractTemperatureChanger {

    /**
     * Default constructor.
     * @param minIntensity
     * @param maxIntensity
     * @param currentIntensity
     * @param environment
     * @param minTemperature
     * @param maxTemperature
     */
    public Heating(final double minIntensity, final double maxIntensity, final double currentIntensity, 
        final Environment environment, final Temperature minTemperature, final Temperature maxTemperature) {
        super(minIntensity, maxIntensity, currentIntensity, environment, minTemperature, maxTemperature);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        var oldTemp = this.getEnvironment().getTemperature().getCelsius();
        var updatedTemp = oldTemp + this.getState() * deltaTime.toMillis() * this.getStep();
        var newTemp = Math.min(this.getMaxTemperature().getCelsius(), updatedTemp);
        this.getEnvironment().setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

}
