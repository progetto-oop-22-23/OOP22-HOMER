package homer.impl;

import homer.api.DeviceInfo;
import homer.api.ToggleableDevice;

public final class Light<S> implements ToggleableDevice<S> {
    private final long id;
    private final String type;
    private S state;

    public Light(final long id, final S state) {
        this.id = id;
        this.type = "Light";
        this.state = state;
    }

    @Override
    public DeviceInfo getInfo() {
        return new DeviceInfo() {
            @Override
            public long getID() {
                return id;
            }

            @Override
            public String getType() {
                return type;
            }
        };
    }

    @Override
    public S getState() {
        return this.state;
    }

    @Override
    public boolean isToggled() {
        return ((Boolean) this.getState()).booleanValue();
    }

    @Override
    public void toggle() {
    }
}
