package homer.api;

/**
 * This interface models a domotic device.
 * 
 * @param <S> The device state.
 */
public interface Device<S> {

    /**
     * Returns the device state parameters.
     * For example on/off state, intensity.
     * 
     * @return the device state parameters.
     */
    S getState();

}
