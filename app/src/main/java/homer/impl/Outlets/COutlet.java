package homer.impl.Outlets;

import homer.api.Device;
import homer.api.DeviceInfo;

/**
 * Models a standard C-type outlet, which supports a maximum power drain of 2kW.
 * See {@link homer.impl.Outlets.Outlet}
 */

public final class COutlet extends Outlet {

    private static final double MAX_VALUE = 2.0;
    private static final double MIN_VALUE = 0;

    /**
     * COutlet constructor.
     * 
     * @param info
     * @param state
     * @param device
     */
    public COutlet(final DeviceInfo info, final double state, final Device<?> device) {
        super(info, state, MIN_VALUE, MAX_VALUE, device);
    }

}
