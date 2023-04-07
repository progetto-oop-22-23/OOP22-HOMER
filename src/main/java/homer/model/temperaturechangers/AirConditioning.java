package homer.model.temperaturechangers;

import java.time.Duration;

import homer.common.temperature.TemperatureFactory;
import homer.common.time.DurationConverter;
import homer.model.environment.Environment;

/**
 * Models air conditioning.
 */
public final class AirConditioning extends AbstractTemperatureChanger {

    /**
     * @param minIntensity the minimum intensity allowed.
     * @param maxIntensity the maximum intensity allowed.
     * @param environment  the environment that is modified by the heating device.
     */
    public AirConditioning(final double minIntensity, final double maxIntensity,
            final Environment environment) {
        super(minIntensity, maxIntensity, environment);
    }

    @Override
    void updateTemperature(final Duration deltaTime) {
        final double oldTemp = this.getEnvironment().getTemperature().getCelsius();
        final double newTemp = Math.max(this.getMinTemperature().getCelsius(),
                oldTemp - this.getState().getCurrentIntensity().get() * DurationConverter.toHours(deltaTime));
        this.getEnvironment().setTemperature(TemperatureFactory.fromCelsius(newTemp));
    }

    @Override
    public TemperatureChangerState getState() {
        return super.getTemperatureState().addTemperatureChangerType(TemperatureChangerState.AIRCONDITIONING);
    }

}
