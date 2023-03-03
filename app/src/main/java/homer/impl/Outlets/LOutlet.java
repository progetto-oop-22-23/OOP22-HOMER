package homer.impl.Outlets;

import homer.api.Device;
import homer.api.DeviceInfo;

/**
 * Models a standard L-type outlet, which supports a maximum power drain of
 * 3.5kW.
 * See {@link homer.impl.Outlets.Outlet}
 */

public final class LOutlet extends Outlet {

    private static final double MAX_VALUE = 3.5;
    private static final double MIN_VALUE = 0;

    /**
     * LOutlet constructor.
     * 
     * @param info
     * @param state
     * @param device
     */
    public LOutlet(final DeviceInfo info, final double state, final Device<?> device) {
        super(info, state, MIN_VALUE, MAX_VALUE, device);
    }

}
