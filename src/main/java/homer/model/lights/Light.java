package homer.model.lights;

import java.time.Duration;
import java.util.Objects;

import homer.api.DeviceInfo;
import homer.api.PoweredDevice;
import homer.api.ToggleableDevice;
import homer.common.limit.Limit;
import homer.core.DiscreteObject;
import homer.model.outlets.Outlet;

/**
 * Models lights in the house.
 * 
 * @author Alessandro Monticelli
 */
public final class Light implements ToggleableDevice<Boolean>, PoweredDevice, DiscreteObject {

    private final double minConsumption;
    private final double maxConsumption;
    private Outlet outlet;
    private double istantConsumption;
    private final DeviceInfo info;
    private Boolean state;

    /**
     * Constructor for class Light.
     * 
     * @param info           See {@link homer.api.DeviceInfo}.
     * @param state          On/Off.
     * @param minConsumption The minimum electrical consumption.
     * @param maxConsumption The maximum electrical consumption.
     * @param outlet         The {@link homer.model.outlets.Outlet} the
     *                       {@code Light} is plugged to.
     */
    public Light(final DeviceInfo info, final Boolean state, final double maxConsumption,
            final Outlet outlet) {
        this.info = Objects.requireNonNull(info);
        this.state = Objects.requireNonNull(state);
        this.outlet = new Outlet(outlet.getInfo(), outlet.getState(), outlet.getMinValue(), outlet.getMaxValue());
        this.minConsumption = 0.0;
        this.maxConsumption = Limit.limit(maxConsumption, outlet.getMinValue(), outlet.getMaxValue());
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
        this.maxConsumption = 10;   //Watts
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
        final double intensity = this.getState() ? 1.0 : 0.0;
        final double powerConsumption = Math.min(
                Math.max(this.getMaxConsumption() - deltaTime.toSeconds() * intensity, 0.0),
                this.getMaxConsumption());
        this.setIstantConsumption(powerConsumption);
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

    @Override
    public void plug(final Outlet outlet) {
        this.outlet = new Outlet(outlet.getInfo(), outlet.getState(), outlet.getMinValue(), outlet.getMaxValue());
    }

    /**
     * Returns the {@link homer.model.outlets.Outlet} the device is plugged to.
     * 
     * @return outlet.
     */
    public Outlet getOutlet() {
        return new Outlet(this.outlet.getInfo(), this.outlet.getState(), this.outlet.getMinValue(),
                this.outlet.getMaxValue());
    }

}
