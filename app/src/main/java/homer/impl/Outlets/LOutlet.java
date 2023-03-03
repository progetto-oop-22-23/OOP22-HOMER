package homer.impl.Outlets;

import homer.api.Device;
import homer.api.DeviceInfo;

/**
 * Models a standard L-type outlet, which supports a maximum power drain of 3.5kW.
 * See {@link homer.impl.Outlets.Outlet}
 */

public final class LOutlet extends Outlet {
    /**
     * LOutlet constructor.
     * 
     * @param info
     * @param state
     * @param minValue 0kW
     * @param maxValue 3.5kW
     * @param device
     */
    public LOutlet(DeviceInfo info, double state, Device<?> device) {
        super(info, state, 0, 3.5, device);
    }

}