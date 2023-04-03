package homer.model.airquality;

import java.time.Duration;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.api.Device;
import homer.core.DiscreteObject;
import homer.model.environment.Environment;

/**
 * Simple air quality sensor.
 */
public final class AirqualitySensor implements Device<AirQualityState>, DiscreteObject {

    private final Environment environment;
    private AirQualityState airQualityState;

    /**
     * 
     * @param environment
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Reference is needed in order for this to work")
    public AirqualitySensor(final Environment environment) {
        this.environment = Objects.requireNonNull(environment);
        updateAirQualityState();
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        updateAirQualityState();
    }

    @Override
    public AirQualityState getState() {
        return new AirQualityStateImpl(this.airQualityState);
    }

    private void updateAirQualityState() {
        this.airQualityState = new AirQualityStateImpl(environment.getAirQualityState());
    }
}
