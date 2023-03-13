package homer.api;

/**
 * This interface models a device whose state can vary between more than two
 * possible values.
 * 
 * @param <S> The device state.
 */
public interface AdjustableDevice<S> extends Device<S> {

    /**
     * 
     * @return The min value the device state can be set to.
     */
    S getMinValue();

    /**
     * 
     * @return The max value the device state can be set to.
     */
    S getMaxValue();

    /**
     * Sets the device state to the new value.
     * 
     * @param state The new commanded level.
     */
    void setState(S state);
}
