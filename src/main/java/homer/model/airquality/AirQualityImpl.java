package homer.model.airquality;

import java.time.Duration;
import java.util.Random;

import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

/**
 * Every value is updated with random gaussian values, and will always be above zero.
 */
public final class AirQualityImpl implements AirQuality {

    private final Environment environment;
    private final Random random = new Random();

    /**
     * 
     * @param environment the environment to associate to the device.
     */
    public AirQualityImpl(final Environment environment) {
        this.environment = new HomeEnvironment(environment);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final AirQualityState old  = this.environment.getAirQualityState();
        final double newco2 = updatedValue(old.getCO2(), deltaTime);
        final double newpm10 = updatedValue(old.getPM10(), deltaTime);
        final double newtoxic = updatedValue(old.getToxicGasPercentage(), deltaTime);
        final double newpm25 = updatedValue(old.getPM25(), deltaTime);
        this.environment.setAirQualityState(new AirQualityStateImpl(
            Math.max(newco2, 0), Math.max(0, newpm10), Math.max(0, newtoxic), Math.max(0, newpm25)));
    }

    /**
     * 
     * @param previous previous value
     * @param deltaTime time elapsed since last update
     * @return value after time elapsed. Will be greater or equal than 0
     */
    private double updatedValue(final double previous, final Duration deltaTime) {
        return Math.max(0, random.nextGaussian() * deltaTime.toHours() + previous);
    }
}
