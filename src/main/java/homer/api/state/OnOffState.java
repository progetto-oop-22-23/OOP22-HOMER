package homer.api.state;

import homer.api.DeviceState;

/**
 * Implementation of {@link DeviceState} for a {@link ToggleableDevice}.
 */
public final class OnOffState implements DeviceState {

    private final boolean isOn;

    /**
     * Creates a new {@link OnOffState}.
     * 
     * @param isOn the current state.
     */
    public OnOffState(final boolean isOn) {
        this.isOn = isOn;
    }

    /**
     * Returns whether the device is activated.
     * 
     * @return whether the device is activated.
     */
    public boolean isOn() {
        return this.isOn;
    }

}
