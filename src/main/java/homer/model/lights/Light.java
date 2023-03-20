package homer.model.lights;

import java.time.Duration;
import java.util.Objects;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.api.PoweredDevice;
import homer.api.PoweredDeviceInfo;
import homer.api.PoweredDeviceInfoImpl;
import homer.api.ToggleableDevice;
import homer.common.limit.Limit;
import homer.core.DiscreteObject;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;

/**
 * Models lights in the house.
 * 
 * @author Alessandro Monticelli
 */
public final class Light implements ToggleableDevice<Boolean>, PoweredDevice, DiscreteObject {

    private final DeviceInfo info;
    private Boolean state;
    private final PoweredDeviceInfo power;
    private double instantConsumption;

    /**
     * Constructor for class Light.
     * 
     * @param info           See {@link homer.api.DeviceInfo}.
     * @param state          On/Off.
     * @param maxConsumption The maximum electrical consumption.
     * @param outlet         The {@link homer.model.outlets.Outlet} the
     *                       {@code Light} is plugged to.
     */
    public Light(final DeviceInfo info, final Boolean state, PoweredDeviceInfo power) {
        this.info = Objects.requireNonNull(info);
        this.state = Objects.requireNonNull(state);
        this.power = Objects.requireNonNull(power);
        this.instantConsumption = 0.0;
    }

    /**
     * Constructor for class Light. TO BE MODIFIED.
     * 
     * @param info  See {@link homer.api.DeviceInfo}.
     * @param state On/Off.
     */
    public Light(final DeviceInfo info, final Boolean state) {
        this.info = Objects.requireNonNull(info);
        this.state = Objects.requireNonNull(state);
        this.power = new PoweredDeviceInfoImpl(this.info.getID(), this.info.getType(), 10,
                OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0));
        this.instantConsumption = 0.0;
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
        final double intensity = this.getState() ? 1.0 : 0.0;
        final double powerConsumption = Math.min(
                Math.max(this.power.getMaxConsumption() - deltaTime.toSeconds() * intensity, 0.0),
                this.power.getMaxConsumption());
        this.setIstantConsumption(powerConsumption);
    }

    @Override
    public double getIstantConsumption() {
        return this.instantConsumption;
    }

    @Override
    public void setIstantConsumption(final double instantConsumption) {
        this.instantConsumption = instantConsumption;
    }

    @Override
    public void plug(Outlet outlet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'plug'");
    }

    @Override
    public PoweredDeviceInfo getPowerInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPowerInfo'");
    }

}
