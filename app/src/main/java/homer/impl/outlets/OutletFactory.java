package homer.impl.outlets;

import homer.api.DeviceInfo;

public class OutletFactory {

    /**
     * Intantiates a standard C-type outlet (max power absorption 2kW).
     * See {@link homer.impl.outlets.Outlet}
     * @param info      
     * @param state
     * @param device
     * @return
     */
    public static <S> Outlet<S> cOutlet(DeviceInfo info, int state, S device) {
        return new Outlet<S>(info, state, 0.0, 2.0, device);
    }

    /**
     * Intantiates a standard L-type outlet (max power absorption 3.5kW).
     * 
     * @param info
     * @param state
     * @param device
     * @return
     */
    public static <S> Outlet<S> lOutlet(DeviceInfo info, int state, S device) {
        return new Outlet<S>(info, state, 0.0, 3.5, device);
    }
}
