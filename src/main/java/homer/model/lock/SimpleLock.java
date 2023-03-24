package homer.model.lock;

/**
 * Implementation of {@link Lock}.
 */
public final class SimpleLock implements Lock {

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
