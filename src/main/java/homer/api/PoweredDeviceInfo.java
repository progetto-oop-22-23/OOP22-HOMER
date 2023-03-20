package homer.api;

import homer.model.outlets.Outlet;

/**
 * Interface for the {@link homer.api.PoweredDevice} infos to report to the Controller.
 */
public interface PoweredDeviceInfo {

    /**
     * Returns the {@link homer.model.outlets.Outlet} the device is plugged to.
     * @return {@code outlet}
     */
    Outlet getOutlet();

    /**
     * Returns the minimum electrical consumption of the device.
     * @return {@code minConsumption}
     */
    double getMinConsumption();

    /**
     * Returns the maximum electrical consumption of the device.
     * @return {@code maxConsumption}
     */
    double getMaxConsumption();

}
