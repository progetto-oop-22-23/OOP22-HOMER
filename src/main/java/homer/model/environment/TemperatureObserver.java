package homer.model.environment;

import homer.common.temperature.Temperature;

/**
 * Temperature observer interface.
 */
public interface TemperatureObserver {

    /**
     * Sets a new temperature.
     * 
     * @param temperature the new temperature.
     */
    void setTemperature(Temperature temperature);

}
