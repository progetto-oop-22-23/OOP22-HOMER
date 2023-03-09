package homer.model.temperaturechangers;

import homer.api.AdjustableDevice;
import homer.core.DiscreteObject;

/**
 * Temperature changer device have an adjustable intensity.
 */
public interface TemperatureChanger extends AdjustableDevice<Double>, DiscreteObject {

}
