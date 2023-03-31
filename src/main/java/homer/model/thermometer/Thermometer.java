package homer.model.thermometer;

import homer.api.Device;
import homer.api.state.ThermometerState;
import homer.core.DiscreteObject;

/**
 * This interface models a thermometer {@link Device}.
 */
public interface Thermometer extends Device<ThermometerState>, DiscreteObject {

}
