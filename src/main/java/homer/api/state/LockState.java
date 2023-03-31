package homer.api.state;

import homer.api.DeviceState;

/**
 * Implementation of {@link DeviceState} for a {@link ToggleableDevice}.
 */
public final class LockState implements DeviceState {

    private final boolean isOn;

    /**
     * Creates a new {@link LockState}.
     * 
     * @param isOn the current state.
     */
    public LockState(final boolean isOn) {
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
