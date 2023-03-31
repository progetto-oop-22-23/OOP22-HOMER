package homer.model.lights;

import homer.api.DeviceState;

/**
 * Implementation of {@link DeviceState} for a {@link Light} device.
 */
public final record LightState(boolean isOn) implements DeviceState {

}
