package homer.model.lock;

import homer.api.state.LockState;

/**
 * Implementation of {@link Lock}.
 */
public final class SimpleLock implements Lock {

    private boolean isLocked = false;

    @Override
    public LockState getState() {
        return new LockState(this.isLocked);
    }

    @Override
    public boolean isToggled() {
        return getState().isOn();
    }

    @Override
    public void toggle() {
        this.isLocked = !this.isLocked;
    }

}
