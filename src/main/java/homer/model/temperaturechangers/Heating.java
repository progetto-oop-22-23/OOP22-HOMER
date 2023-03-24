package homer.model.temperaturechangers;

import java.time.Duration;

import homer.common.temperature.TemperatureFactory;
import homer.common.time.DurationConverter;
import homer.model.environment.Environment;

/**
 * Models a heating device.
 */
public final class Heating extends AbstractTemperatureChanger {

    /**
     * @param minIntensity
     * @param maxIntensity
     * @param environment
     * @param info
     */
    public Heating(final double minIntensity, final double maxIntensity,
            final Environment environment) {
        super(minIntensity, maxIntensity, environment);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double oldTemp = this.getEnvironment().getTemperature().getCelsius();
        final double updatedTemp = oldTemp + this.getState() * DurationConverter.toHours(deltaTime);
        if (this.getMaxTemperature().isEmpty()) {
            this.getEnvironment().setTemperature(TemperatureFactory.fromCelsius(updatedTemp));
        } else {
            final double cappedTemp = Math.min(this.getMaxTemperature().get().getCelsius(), updatedTemp);
            this.getEnvironment().setTemperature(TemperatureFactory.fromCelsius(cappedTemp));
        }
    }

}
