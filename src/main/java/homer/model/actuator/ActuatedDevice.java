package homer.model.actuator;

import homer.api.AdjustableDevice;
import homer.core.DiscreteObject;

/**
 * This interface models an {@link AdjustableDevice} whose position is
 * controlled in discrete time steps by an {@link Actuator}.
 */
public interface ActuatedDevice extends AdjustableDevice<Integer>, DiscreteObject {
}
