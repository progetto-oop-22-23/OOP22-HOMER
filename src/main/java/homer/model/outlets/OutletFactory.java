package homer.model.outlets;

import homer.api.DeviceInfo;

/**
 * A {@link homer.model.outlets.Outlet} factory.
 * 
 * @author Alessandro Monticelli
 */

public final class OutletFactory {

    private static final double MAX_CTYPE_PW = 2.0; // kW
    private static final double MAX_LTYPE_PW = 3.5; // kW
    private static final double ZERO = 0.0;

    private OutletFactory() {

    }

    /**
     * Instantiates a standard C-type outlet.
     *
     * @param info
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    public static Outlet cOutlet(final DeviceInfo info, final double state) {
        return new Outlet(info, state, ZERO, MAX_CTYPE_PW);
    }

    /**
     * Instantiates a standard L-type outlet.
     * 
     * @param info
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    public static Outlet lOutlet(final DeviceInfo info, final double state) {
        return new Outlet(info, state, ZERO, MAX_LTYPE_PW);
    }
}
