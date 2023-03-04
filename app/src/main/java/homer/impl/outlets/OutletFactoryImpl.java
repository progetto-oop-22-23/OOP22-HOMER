package homer.impl.outlets;

import homer.api.DeviceInfo;

/**
 * A {@link homer.impl.outlets.Outlet} factory.
 * 
 * @author Alessandro Monticelli
 */
public final class OutletFactoryImpl implements OutletFactory {

    private static final double MAX_CTYPE_PW = 2.0; // kW
    private static final double MAX_LTYPE_PW = 3.5; // kW
    private static final double ZERO = 0.0;

    @Override
    public <S> Outlet<S> cOutlet(final DeviceInfo info, final int state, final S device) {
        return new Outlet<S>(info, state, ZERO, MAX_CTYPE_PW, device);
    }

    @Override
    public <S> Outlet<S> lOutlet(final DeviceInfo info, final int state, final S device) {
        return new Outlet<S>(info, state, ZERO, MAX_LTYPE_PW, device);
    }
}
