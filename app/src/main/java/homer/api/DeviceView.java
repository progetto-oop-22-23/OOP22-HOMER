package homer.api;

public interface DeviceView<S> {

    /**
     * 
     * @return Device information to be displayed visually.
     */
    S getState();

}
