package homer.model.lock;

import homer.api.state.OnOffState;

/**
 * Implementation of {@link Lock}.
 */
public final class SimpleLock implements Lock {

    private boolean isLocked = false;

    @Override
    public OnOffState getState() {
        return new OnOffState(this.isLocked);
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
