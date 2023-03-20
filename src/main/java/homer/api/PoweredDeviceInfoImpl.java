package homer.api;

import homer.model.outlets.Outlet;
import homer.DeviceInfoImpl;
import homer.common.limit.Limit;

/**
 * {@link homer.api.PoweredDeviceInfo} implementation.
 * 
 * @author Alessandro Monticelli
 */
public final class PoweredDeviceInfoImpl extends DeviceInfoImpl implements PoweredDeviceInfo {

    private final double minConsumption;
    private final double maxConsumption;
    private final Outlet outlet;

    /**
     * Creates a new instance of {@code PoweredDeviceInfoImpl}.
     * 
     * @param id             The device id
     * @param type           The device type
     * @param maxConsumption The device maximum power consumption
     * @param outlet         The {@link homer.model.outlets.Outlet} the
     *                       {@link homer.api.PoweredDevice} is plugged into
     */
    public PoweredDeviceInfoImpl(final DeviceId id, final String type,
            final double maxConsumption, final Outlet outlet) {
        super(id, type);
        this.outlet = new Outlet(outlet.getInfo(), outlet.getState(), outlet.getMinValue(), outlet.getMaxValue());
        this.minConsumption = 0.0;
        this.maxConsumption = Limit.clamp(maxConsumption, this.outlet.getMinValue(), this.outlet.getMaxValue());
    }

    @Override
    public Outlet getOutlet() {
        return new Outlet(this.outlet.getInfo(), this.outlet.getState(), this.outlet.getMinValue(),
                this.outlet.getMaxValue());
    }

    @Override
    public double getMinConsumption() {
        return this.minConsumption;
    }

    @Override
    public double getMaxConsumption() {
        return this.maxConsumption;
    }

}
