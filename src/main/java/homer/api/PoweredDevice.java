package homer.api;

import homer.model.outlets.Outlet;

/**
 * Models a {@link homer.api.Device} that consumes power.
 * Every PoweredDevice must be plugged into a
 * {@link homer.model.outlets.Outlet}.
 * 
 * @author Alessandro Monticelli
 */
public interface PoweredDevice {

    /**
     * Returns the instant device's consumption.
     * 
     * @return instantConsumption
     */
    double getIstantConsumption();

    /**
     * Sets the instant device's consumption.
     * 
     * @param instantConsumption The new instantConsumption value.
     */
    void setIstantConsumption(double instantConsumption);

    /**
     * Plugs the {@code PoweredDevice} in a {@link home.model.outlets.Outlet}.
     * 
     * @param outlet
     */
    void plug(Outlet outlet);
}
