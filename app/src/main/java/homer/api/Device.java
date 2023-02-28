package homer.api;

public interface Device<S> {

    /**
     * 
     * @return The device information: ID, Type.
     */
    DeviceInfo getInfo();

    /**
     * 
     * @return The device state parameters for eg. on/off, intensity.
     */
    S getState();

}
