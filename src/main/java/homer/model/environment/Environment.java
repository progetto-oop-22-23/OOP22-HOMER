package homer.model.environment;

import homer.model.airquality.AirQualityState;
import homer.common.temperature.Temperature;

/**
 * The environment represents a physical location in the simulation, with its
 * atmospheric parameters.
 */
public interface Environment {

    /**
     * Returns the temperature of the environment.
     * 
     * @return The current temperature of the environment.
     */
    Temperature getTemperature();

    /**
     * Sets the environment temperature to the new value.
     * 
     * @param temperature The new temperature
     */
    void setTemperature(Temperature temperature);

    /**
     * @param airQualityState The new air quality state
     */
    void setAirQualityState(AirQualityState airQualityState);

    /**
     * @return The current air quality state
     */
    AirQualityState getAirQualityState();
}
