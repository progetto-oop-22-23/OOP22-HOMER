package homer.model.outlets;

/**
 * A {@link homer.model.outlets.Outlet} factory.
 * 
 * @author Alessandro Monticelli
 */

public final class OutletFactory {

    private static final double MAX_CTYPE_PW = 2000; // W
    private static final double MAX_LTYPE_PW = 3500; // W
    private static final double ZERO = 0.0;

    private OutletFactory() {

    }

    /**
     * Instantiates a standard C-type outlet.
     *
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    public static Outlet cOutlet(final double state) {
        return new Outlet(state, ZERO, MAX_CTYPE_PW);
    }

    /**
     * Instantiates a standard L-type outlet.
     * 
     * @param state
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    public static Outlet lOutlet(final double state) {
        return new Outlet(state, ZERO, MAX_LTYPE_PW);
    }
}
