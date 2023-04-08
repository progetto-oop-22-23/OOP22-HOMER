package homer.api.state;

import homer.api.DeviceState;

/**
 * Implementation of {@link DeviceState} for a {@link homer.model.lock.Lock}.
 */
public final class LockState implements DeviceState {

    private final boolean isLocked;

    /**
     * Creates a new {@link LockState}.
     * 
     * @param isLocked the current state.
     */
    public LockState(final boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Returns whether the lock is activated.
     * 
     * @return whether the lock is activated.
     */
    public boolean isLocked() {
        return this.isLocked;
    }

}
