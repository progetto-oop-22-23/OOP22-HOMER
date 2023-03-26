package homer.model.outlets;

import homer.api.DeviceState;

/**
 * A {@link homer.model.outlets.Outlet} factory.
 * 
 * @author Alessandro Monticelli
 */

public final class OutletFactory {

    private OutletFactory() {

    }

    /**
     * Instantiates a standard C-type outlet.
     *
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    public static Outlet cOutlet(final double value) {
        final OutletState outletState = new OutletState();
        outletState.addValue(value);
        outletState.addMin(0.0);
        outletState.addMax(2.0);
        return new Outlet(outletState);
    }

    /**
     * Instantiates a standard L-type outlet.
     * 
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    public static Outlet lOutlet(final double value) {
        final OutletState outletState = new OutletState();
        outletState.addValue(value);
        outletState.addMin(0.0);
        outletState.addMax(3.5);
        return new Outlet(outletState);
    }
}
