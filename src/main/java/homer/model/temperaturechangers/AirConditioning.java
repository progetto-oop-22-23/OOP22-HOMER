package homer.model.temperaturechangers;

import java.time.Duration;

import homer.api.DeviceInfo;
import homer.common.TemperatureFactory;
import homer.model.environment.Environment;

/**
 * Models air conditioning.
 */
public final class AirConditioning extends AbstractTemperatureChanger {

    /**
     * @param minIntensity
     * @param maxIntensity
     * @param environment
     * @param info
     */
    public AirConditioning(final double minIntensity, final double maxIntensity, 
    final Environment environment, final DeviceInfo info) {
        super(minIntensity, maxIntensity, environment, info);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double oldTemp = this.getEnvironment().getTemperature().getCelsius();
        final double newTemp = Math.max(this.getMinTemperature().getCelsius(), 
            oldTemp - this.getState() * deltaTime.toMillis() * this.getScaler());
        this.getEnvironment().setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

}
