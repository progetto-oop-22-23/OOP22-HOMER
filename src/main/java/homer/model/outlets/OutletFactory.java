package homer.model.outlets;

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
     * @param value The instant power absorption.
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    public static Outlet cOutlet(final double value) {
        final double maxConsumption = 2000.0;
        final OutletState outletState = new OutletState();
        outletState.addValue(value);
        outletState.addMin(0.0);
        outletState.addMax(maxConsumption);
        return new Outlet(outletState);
    }

    /**
     * Instantiates a standard L-type outlet.
     * 
     * @param value The instant power absorption.
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    public static Outlet lOutlet(final double value) {
        final double maxConsumption = 3500.0;
        final OutletState outletState = new OutletState();
        outletState.addValue(value);
        outletState.addMin(0.0);
        outletState.addMax(maxConsumption);
        return new Outlet(outletState);
    }
}
