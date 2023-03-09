package homer.api;

/**
 * Interface for the device visual representation.
 * 
 * @param <S> The device state.
 */
public interface DeviceView<S> {

    /**
     * 
     * @return Device information to be displayed visually.
     */
    S getState();

}
