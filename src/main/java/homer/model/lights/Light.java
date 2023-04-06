package homer.model.lights;

import java.time.Duration;
import java.util.Objects;

import homer.api.PoweredDevice;
import homer.api.PoweredDeviceInfo;
import homer.api.PoweredDeviceInfoImpl;
import homer.api.ToggleableDevice;
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
public final class Light implements ToggleableDevice<LightState>, PoweredDevice, DiscreteObject {

    private LightState state;
    private final PoweredDeviceInfo power;

    /**
     * Constructor for class Light.
     * 
     * @param state On/Off.
     * @param power See {@link homer.api.PoweredDeviceInfo}.
     */
    public Light(final Boolean state, final PoweredDeviceInfo power) {
        this.state = new LightState(Objects.requireNonNull(state));
        this.power = new PoweredDeviceInfoImpl(power.getMaxConsumption(), power.getOutlet());
    }

    /**
     * Constructor for class Light.
     * 
     * @param state On/Off.
     */
    public Light(final Boolean state) {
        this.state = new LightState(Objects.requireNonNull(state));
        this.power = new PoweredDeviceInfoImpl(10.0,
                OutletFactory.cOutlet(0));
    }

    @Override
    public LightState getState() {
        return new LightState(this.isToggled());
    }

    @Override
    public boolean isToggled() {
        return this.state.isOn();
    }

    @Override
    public void toggle() {
        this.state = new LightState(this.isToggled() ^ true);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double milliseconds = DurationConverter.toMillis(deltaTime);
        final double oneTenth = 0.1;
        final double oneCent = 0.01;
        final double fiveCents = 0.05;
        double intensity = 0.0;
        if (this.isToggled()) {
            intensity = Math.sin(milliseconds * oneTenth) - (oneCent + Math.random() * fiveCents);
        }
        final double maxConsumption = this.getPowerInfo().getMaxConsumption();
        final double newConsumption = maxConsumption * Math.pow(intensity, 2) * milliseconds;
        this.setInstantConsumption(
                Limit.clamp(newConsumption, this.getPowerInfo().getMinConsumption(), maxConsumption));
    }

    @Override
    public double getInstantConsumption() {
        return this.getPowerInfo().getOutlet().getState().getPower().get();
    }

    @Override
    public void setInstantConsumption(final double instantConsumption) {
        this.power.getOutlet().getState().addValue(instantConsumption);
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
