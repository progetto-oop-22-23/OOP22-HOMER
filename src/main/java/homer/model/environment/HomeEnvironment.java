package homer.model.environment;

import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirQualityStateImpl;
import homer.common.temperature.KelvinTemperature;
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
     * @param airQualityState The initial state of the air quality.
     */
    public HomeEnvironment(final Temperature temperature, final AirQualityState airQualityState) {
        this.temperature = new KelvinTemperature(temperature.getKelvin());
        this.airQualityState = new AirQualityStateImpl(airQualityState);
    }

    /**
     * Creates a new {@link HomeEnvironment} from an environment.
     * @param environment the old environmnet
     */
    public HomeEnvironment(final Environment environment) {
        this.temperature = environment.getTemperature();
        this.airQualityState = environment.getAirQualityState();
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
        this.airQualityState = new AirQualityStateImpl(airQualityState);
    }

    @Override
    public AirQualityState getAirQualityState() {
        return new AirQualityStateImpl(this.airQualityState);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((temperature == null) ? 0 : temperature.hashCode());
        result = prime * result + ((airQualityState == null) ? 0 : airQualityState.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HomeEnvironment other = (HomeEnvironment) obj;
        if (temperature == null) {
            if (other.temperature != null)
                return false;
        } else if (!temperature.equals(other.temperature))
            return false;
        if (airQualityState == null) {
            if (other.airQualityState != null)
                return false;
        } else if (!airQualityState.equals(other.airQualityState))
            return false;
        return true;
    }

}
