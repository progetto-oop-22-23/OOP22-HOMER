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
     * 
     * @return The current value at which the device is operating.
     */
    S getValue();

    /**
     * Sets the device state to the new value.
     * 
     * @param value The new commanded level.
     */
    void setValue(S value);

}
