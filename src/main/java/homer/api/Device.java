package homer.api;

/**
 * This interface models a domotic device.
 * 
 * @param <S> The device state.
 */
public interface Device<S> {

    /**
     * 
     * @return The device state parameters for eg. on/off, intensity.
     */
    S getState();

}
