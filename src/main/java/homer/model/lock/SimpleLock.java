package homer.model.lock;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.api.ToggleableDevice;

/**
 * Implementation of a {@link ToggleableDevice} representing a lock.
 */
public final class SimpleLock implements ToggleableDevice<Boolean> {

    /**
     * Type of device.
     */
    public static final String DEVICE_TYPE = "SimpleLock";
    private boolean isLocked = false;

    @Override
    public DeviceInfo getInfo() {
        return new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    }

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
