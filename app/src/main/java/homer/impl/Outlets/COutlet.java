package homer.impl.Outlets;

import homer.api.Device;
import homer.api.DeviceInfo;

/**
 * Models a standard C-type outlet, which supports a maximum power drain of 2kW.
 * See {@link homer.impl.Outlets.Outlet}
 */

public final class COutlet extends Outlet {
    /**
     * COutlet constructor.
     * @param info
     * @param state
     * @param minValue    0kW
     * @param maxValue    2kW
     * @param device
     */
    public COutlet(DeviceInfo info, double state, double minValue, double maxValue, Device<?> device) {
        super(info, state, 0, 2, device);
    }
    
}