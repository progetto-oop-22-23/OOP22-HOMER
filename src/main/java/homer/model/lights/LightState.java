package homer.model.lights;

import homer.api.DeviceState;

/**
 * Implementation of {@link DeviceState} for a {@link Light} device.
 * @param isOn Whether the light is on or not.
 */
public record LightState(boolean isOn) implements DeviceState {

}
