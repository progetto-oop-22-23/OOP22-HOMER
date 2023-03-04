package homer.model.outlets;

import homer.api.Device;
import homer.api.DeviceInfo;

/**
 * A {@link homer.model.outlets.Outlet} factory.
 * 
 * @author Alessandro Monticelli
 */

public interface OutletFactory {

    /**
     * Instantiates a standard C-type outlet.
     *
     * @param info
     * @param state
     * @param device
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    Outlet cOutlet(DeviceInfo info, int state, Device<?> device);

    /**
     * Instantiates a standard C-type outlet.
     *
     * @param info
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    Outlet cOutlet(DeviceInfo info, int state);

    /**
     * Instantiates a standard L-type outlet.
     * 
     * @param info
     * @param state
     * @param device
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    Outlet lOutlet(DeviceInfo info, int state, Device<?> device);

    /**
     * Instantiates a standard L-type outlet.
     * 
     * @param info
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    Outlet lOutlet(DeviceInfo info, int state);
}
