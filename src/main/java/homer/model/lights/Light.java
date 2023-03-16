package homer.model.lights;

import java.time.Duration;
import java.util.Objects;

import homer.api.DeviceInfo;
import homer.api.PoweredDevice;
import homer.api.ToggleableDevice;

/**
 * Models lights in the house.
 * 
 * @author Alessandro Monticelli
 */
public final class Light implements ToggleableDevice<Boolean>, PoweredDevice<Boolean> {

    private final double minConsumption;
    private final double maxConsumption;
    private double istantConsumption;
    private final DeviceInfo info;
    private Boolean state;

    /**
     * Constructor for class Light.
     * 
     * @param info  See {@link homer.api.DeviceInfo}.
     * @param state On/Off.
     * @param minConsumption The minimum electrical consumption.
     * @param maxConsumption The maximum electrical consumption.
     */
    public Light(final DeviceInfo info, final Boolean state, final double minConsumption, final double maxConsumption) {
        this.info = Objects.requireNonNull(info);
        this.state = Objects.requireNonNull(state);
        this.minConsumption = minConsumption;
        this.maxConsumption = maxConsumption;
        this.istantConsumption = 0.0;
    }

    /**
     * Constructor for class Light. TO BE REMOVED OR MODIFIED.
     * 
     * @param info  See {@link homer.api.DeviceInfo}.
     * @param state On/Off.
     */
    public Light(final DeviceInfo info, final Boolean state) {
        this.info = Objects.requireNonNull(info);
        this.state = Objects.requireNonNull(state);
        this.minConsumption = 0.0;
        this.maxConsumption = 0.0;
        this.istantConsumption = 0.0;
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
        this.state ^= true;
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTick'");
    }

    @Override
    public double getMinConsumption() {
        return this.minConsumption;
    }

    @Override
    public double getMaxConsumption() {
        return this.maxConsumption;
    }

    @Override
    public double getIstantConsumption() {
        return this.istantConsumption;
    }

    @Override
    public void setIstantConsumption(final double istantConsumption) {
        this.istantConsumption = istantConsumption;
    }

}
