package homer.model.airquality;

import java.time.Duration;
import java.util.Objects;

import homer.api.Device;
import homer.core.DiscreteObject;
import homer.model.environment.Environment;

/**
 * Simple air quality sensor.
 */
public class AirqualitySensor implements Device<AirQualityState>, DiscreteObject {

    private Environment environment;
    private AirQualityState airQualityState; 

    /**
     * 
     * @param environment
     */
    public AirqualitySensor(final Environment environment) {
        this.environment = Objects.requireNonNull(environment);
    }

    @Override
    public void updateTick(Duration deltaTime) {
        this.airQualityState = new AirQualityStateImpl(environment.getAirQualityState());
    }

    @Override
    public AirQualityState getState() {
        return this.airQualityState;        
    }
    
}
