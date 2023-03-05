package homer.model.lights;

import java.util.Objects;

import homer.api.DeviceInfo;
import homer.api.ToggleableDevice;

/**
 * Models lights in the house.
 * 
 * @author Alessandro Monticelli
 */
public class Light implements ToggleableDevice<Boolean> {

    private final DeviceInfo info;
    private Boolean state;

    /**
     * Constructor for class Light
     * 
     * @param info  See {@link homer.api.DeviceInfo}
     * @param state On/Off
     */
    public Light(final DeviceInfo info, final Boolean state) {
        this.info = Objects.requireNonNull(info);
        this.state = Objects.requireNonNull(state);
    }

    @Override
    public DeviceInfo getInfo() {

        return this.info;
    }

    @Override
    public Boolean getState() {
        return this.state;
    }

    @Override
    public boolean isToggled() {
        return this.getState();
    }

    @Override
    public void toggle() {
        this.state = (this.isToggled() ^ true);
    }

}
