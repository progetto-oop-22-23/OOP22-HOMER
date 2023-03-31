package homer.api;

import homer.model.outlets.Outlet;
import homer.model.outlets.OutletState;
import homer.common.limit.Limit;

/**
 * {@link homer.api.PoweredDeviceInfo} implementation.
 * 
 * @author Alessandro Monticelli
 */
public final class PoweredDeviceInfoImpl implements PoweredDeviceInfo {

    private final double minConsumption;
    private final double maxConsumption;
    private Outlet outlet;

    /**
     * Creates a new instance of {@code PoweredDeviceInfoImpl}.
     * 
     * @param maxConsumption The device maximum power consumption
     * @param outlet         The {@link homer.model.outlets.Outlet} the
     *                       {@link homer.api.PoweredDevice} is plugged into
     */
    public PoweredDeviceInfoImpl(final double maxConsumption, final Outlet outlet) {
        this.outlet = new Outlet(outlet);
        final OutletState outletState = (OutletState) outlet.getState();
        this.minConsumption = 0.0;
        this.maxConsumption = Limit.clamp(maxConsumption, outletState.getMin().get() * 1000,
                outletState.getMax().get() * 1000);
    }

    @Override
    public Outlet getOutlet() {
        return new Outlet(this.outlet);
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
    public void setOutlet(final Outlet outlet) {
        this.outlet = new Outlet(outlet);
    }

}
