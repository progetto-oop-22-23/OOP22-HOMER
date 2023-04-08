package homer.model.lock;

import homer.api.state.LockState;

/**
 * Implementation of {@link Lock}.
 */
public final class SimpleLock implements Lock {

    private boolean isLocked;

    @Override
    public LockState getState() {
        return new LockState(this.isLocked);
    }

    @Override
    public boolean isToggled() {
        return getState().isLocked();
    }

    @Override
    public void toggle() {
        this.isLocked = !this.isLocked;
    }

}
