package homer.impl.outlets;

import homer.api.DeviceInfo;

/**
 * A {@link homer.impl.outlets.Outlet} factory.
 * 
 * @author Alessandro Monticelli
 */
public class OutletFactoryImpl {

    private static double MAX_CTYPE_PW = 2.0; // kW
    private static double MAX_LTYPE_PW = 3.5; // kW
    private static double ZERO = 0.0;

    /**
     * Intantiates a standard C-type outlet (max power absorption 2kW).
     * 
     * @param <S>    Device generic type
     * @param info
     * @param state
     * @param device
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    public static <S> Outlet<S> cOutlet(final DeviceInfo info, final int state, final S device) {
        return new Outlet<S>(info, state, ZERO, MAX_CTYPE_PW, device);
    }

    /**
     * Intantiates a standard L-type outlet (max power absorption 3.5kW).
     * 
     * @param <S>    Device generic type
     * @param info
     * @param state
     * @param device
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    public static <S> Outlet<S> lOutlet(final DeviceInfo info, final int state, final S device) {
        return new Outlet<S>(info, state, ZERO, MAX_LTYPE_PW, device);
    }
}
