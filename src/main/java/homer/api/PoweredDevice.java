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
     * @param outlet the outlet where to plug the {@code PoweredDevice}
     */
    void plug(Outlet outlet);

    /**
     * Returns infos about power consumption and the outlet the device is plugged
     * into.
     * 
     * @return {@link homer.api.PoweredDeviceInfo}.
     */
    PoweredDeviceInfo getPowerInfo();
}
