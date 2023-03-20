package homer.api;

import homer.model.outlets.Outlet;

/**
 * Interface for the {@link homer.api.PoweredDevice} infos to report to the
 * Controller.
 */
public interface PoweredDeviceInfo {

    /**
     * Returns the {@link homer.model.outlets.Outlet} the device is plugged to.
     * 
     * @return {@code outlet}
     */
    Outlet getOutlet();

    /**
     * Sets the {@link homer.model.outlets.Outlet} the device must be plugged to.
     * 
     * @param outlet the new {@link homer.model.outlets.Outlet}.
     */
    void setOutlet(Outlet outlet);

    /**
     * Returns the minimum electrical consumption of the device.
     * 
     * @return {@code minConsumption}
     */
    double getMinConsumption();

    /**
     * Returns the maximum electrical consumption of the device.
     * 
     * @return {@code maxConsumption}
     */
    double getMaxConsumption();

}
