package homer.model.lock;

import homer.api.ToggleableDevice;

/**
 * Implementation of a {@link ToggleableDevice} representing a lock.
 */
public final class SimpleLock implements ToggleableDevice<Boolean> {

    private boolean isLocked = false;

    @Override
    public Boolean getState() {
        return this.isLocked;
    }

    @Override
    public boolean isToggled() {
        return getState();
    }

    @Override
    public void toggle() {
        this.isLocked = !this.isLocked;
    }

}
