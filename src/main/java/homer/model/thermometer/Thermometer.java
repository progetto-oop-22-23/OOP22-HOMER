package homer.model.thermometer;

import homer.api.Device;
import homer.common.temperature.Temperature;
import homer.model.environment.TemperatureObserver;

/**
 * This interface models a thermometer {@link Device}.
 */
public interface Thermometer extends Device<Temperature>, TemperatureObserver {

}
