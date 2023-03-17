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
        final double newco2 = random.nextGaussian()  * deltaTime.toHours() +  old.getCO2();
        final double newpm10 = random.nextGaussian() * deltaTime.toHours() + old.getPM10();
        final double newtoxic = random.nextGaussian() * deltaTime.toHours() + old.getToxicGasPercentage();
        final double newpm25 = random.nextGaussian() * deltaTime.toHours() + old.getPM25();
        this.environment.setAirQualityState(new AirQualityStateImpl(
            Math.max(newco2, 0), Math.max(0, newpm10), Math.max(0, newtoxic), Math.max(0, newpm25)));
    }
}
