package homer.model.temperaturechangers;

import homer.api.AdjustableDevice;
import homer.common.temperature.Temperature;
import homer.core.DiscreteObject;

/**
 * Temperature changer device have an adjustable intensity.
 */
public interface TemperatureChanger extends AdjustableDevice<Double>, DiscreteObject {

    /**
     * @param temperature new maximum temperature
     */
    void setMaxTemperature(Temperature temperature);

    /**
     * @param temperature new minimum temperature
     */
    void setMinTemperature(Temperature temperature);
}
