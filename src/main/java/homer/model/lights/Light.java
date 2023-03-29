package homer.model.lights;

import java.time.Duration;
import java.util.Objects;

import homer.api.PoweredDevice;
import homer.api.PoweredDeviceInfo;
import homer.api.PoweredDeviceInfoImpl;
import homer.api.ToggleableDevice;
import homer.api.state.OnOffState;
import homer.common.limit.Limit;
import homer.common.time.DurationConverter;
import homer.core.DiscreteObject;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;

/**
 * Models lights in the house.
 * 
 * @author Alessandro Monticelli
 */
public final class Light implements ToggleableDevice<OnOffState>, PoweredDevice, DiscreteObject {

    private OnOffState state;
    private final PoweredDeviceInfo power;
    private double instantConsumption;

    /**
     * Constructor for class Light.
     * 
     * @param state On/Off.
     * @param power See {@link homer.api.PoweredDeviceInfo}.
     */
    public Light(final Boolean state, final PoweredDeviceInfo power) {
        this.state = new OnOffState(Objects.requireNonNull(state));
        this.power = new PoweredDeviceInfoImpl(power.getMaxConsumption(), power.getOutlet());
        this.instantConsumption = 0.0;
    }

    /**
     * Constructor for class Light.
     * 
     * @param state On/Off.
     */
    public Light(final Boolean state) {
        this.state = new OnOffState(Objects.requireNonNull(state));
        this.power = new PoweredDeviceInfoImpl(10.0,
                OutletFactory.cOutlet(0));
        this.instantConsumption = 0.0;
    }

    @Override
    public OnOffState getState() {
        return new OnOffState(this.isToggled());
    }

    @Override
    public boolean isToggled() {
        return this.state.isOn();
    }

    @Override
    public void toggle() {
        this.state = new OnOffState(this.isToggled() ^ true);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double oldConsumption = this.getInstantConsumption();
        final double maxConsumption = this.getPowerInfo().getMaxConsumption();
        final double milliseconds = DurationConverter.toMillis(deltaTime);
        double intensity = 0.0;
        if (this.isToggled()) {
            intensity = (Math.sin(milliseconds * 0.1) - (0.01 + Math.random() * 0.05));
        }
        final double newConsumption = oldConsumption + intensity * milliseconds;
        this.setInstantConsumption(
                Limit.clamp(newConsumption, this.getPowerInfo().getMinConsumption(), maxConsumption));
    }

    @Override
    public double getInstantConsumption() {
        return this.instantConsumption;
    }

    @Override
    public void setInstantConsumption(final double instantConsumption) {
        this.instantConsumption = instantConsumption;
    }

    @Override
    public void plug(final Outlet outlet) {
        this.getPowerInfo().setOutlet(outlet);
    }

    @Override
    public PoweredDeviceInfo getPowerInfo() {
        return new PoweredDeviceInfoImpl(this.power.getMaxConsumption(), this.power.getOutlet());
    }

}
