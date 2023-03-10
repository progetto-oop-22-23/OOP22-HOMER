package homer.model.temperaturechangers;

import java.time.Duration;

import homer.common.Temperature;
import homer.common.TemperatureFactory;
import homer.model.environment.Environment;

/**
 * Models air conditioning.
 */
public final class AirConditioning extends AbstractTemperatureChanger {


    /**
     * 
     * @param minIntensity
     * @param maxIntensity
     * @param currentIntensity
     * @param environment
     * @param minTemperature
     * @param maxTemperature
     */
    public AirConditioning(final double minIntensity, final double maxIntensity, final double currentIntensity, 
        final Environment environment, final Temperature minTemperature, final Temperature maxTemperature) {
        super(minIntensity, maxIntensity, currentIntensity, environment, minTemperature, maxTemperature);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double oldTemp = this.getEnvironment().getTemperature().getCelsius();
        final double newTemp = Math.max(this.getMinTemperature().getCelsius(), 
            oldTemp - this.getState() * deltaTime.toMillis() * this.getNormalizer());
        this.getEnvironment().setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

}
