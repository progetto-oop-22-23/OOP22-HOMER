package homer.impl;

import homer.api.DeviceInfo;
import homer.api.ToggleableDevice;

public final class Light implements ToggleableDevice<Boolean> {
    private final long id;
    private final String type;
    private Boolean state;

    public Light(final long id, final Boolean state) {
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

    public void setState(final Boolean state) {
        this.state = state;
    }

    @Override
    public Boolean getState() {
        return this.state;
    }

    @Override
    public boolean isToggled() {
        return this.getState().booleanValue();
    }

    @Override
    public void toggle() {
        this.setState(this.getState() ^ Boolean.TRUE);
    }
}
