package homer.impl.outlets;

import homer.api.DeviceInfo;

/**
 * A {@link homer.impl.outlets.Outlet} factory.
 * 
 * @author Alessandro Monticelli
 */

public interface OutletFactory {

    /**
     * Instantiates a standard C-type outlet.
     * 
     * @param <S>    Device generic type
     * @param info
     * @param state
     * @param device
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 2.0}
     */
    <S> Outlet<S> cOutlet(DeviceInfo info, int state, S device);

    /**
     * Instantiates a standard L-type outlet.
     * 
     * @param <S>    Device generic type
     * @param info
     * @param state
     * @param device
     * 
     * @return An Outlet with {@code minValue = 0.0} and {@code maxValue = 3.5}
     */
    <S> Outlet<S> lOutlet(DeviceInfo info, int state, S device);
}
