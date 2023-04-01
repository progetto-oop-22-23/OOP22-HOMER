package homer.model.lights;

import homer.api.DeviceState;

/**
 * Implementation of {@link DeviceState} for a {@link Light} device.
 * @param isOn 
 */
public record LightState(boolean isOn) implements DeviceState {

}
