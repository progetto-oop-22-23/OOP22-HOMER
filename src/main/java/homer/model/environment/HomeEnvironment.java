package homer.model.environment;

import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirQualityStateImpl;
import homer.common.temperature.Temperature;

/**
 * {@link Environment} implementation by modelling the home as one block with no
 * consideration of separate rooms.
 */
public final class HomeEnvironment implements Environment {

    private Temperature temperature;
    private AirQualityState airQualityState;

    /**
     * Creates a new {@link HomeEnvironment}.
     * 
     * @param temperature The initial temperature of the environment.
     */
    public HomeEnvironment(final Temperature temperature, final AirQualityState airQualityState) {
        this.temperature = temperature;
        this.airQualityState = airQualityState;
    }

    @Override
    public Temperature getTemperature() {
        return this.temperature;
    }

    @Override
    public void setTemperature(final Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public void setAirQualityState(final AirQualityState airQualityState) {
        this.airQualityState = airQualityState;
    }

    @Override
    public AirQualityState getAirQualityState() {
        return new AirQualityStateImpl(this.airQualityState);
    }

}
