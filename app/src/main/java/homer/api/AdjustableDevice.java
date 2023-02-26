package homer.api;

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
