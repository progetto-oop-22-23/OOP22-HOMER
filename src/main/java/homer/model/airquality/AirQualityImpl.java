package homer.model.airquality;

import java.time.Duration;
import java.util.Random;

import homer.model.environment.Environment;
import homer.model.environment.HomeEnvironment;

/**
 * Next steps are randomly chosen.
 */
public final class AirQualityImpl implements AirQuality {

    private final Environment environment;
    private final Random random = new Random();
    private static final double CO2DELTA = 1;
    private static final double  PM10DELTA = 1;
    private static final double TOXICDELTA = 1;
    private static final double PM25DELTA = 1;

    /**
     * 
     * @param environment the environment to associate to the device.
     */
    public AirQualityImpl(final Environment environment) {
        this.environment = new HomeEnvironment(environment);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final var old  = this.environment.getAirQualityState();
        final var newco2 = (random.nextGaussian() - 0.5) * CO2DELTA +  old.getCO2();
        final var newpm10 = (random.nextGaussian() - 0.5) * PM10DELTA +  old.getPM10();
        final var newtoxic = (random.nextGaussian() - 0.5) * TOXICDELTA +  old.getToxicGasPercentage();
        final var newpm25 = (random.nextGaussian() - 0.5) * PM25DELTA +  old.getPM25();
        this.environment.setAirQualityState(new AirQualityStateImpl(newco2, newpm10, newtoxic, newpm25));
    }
}
