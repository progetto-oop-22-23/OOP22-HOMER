package homer.api;

/**
 * This interface models a device whose state can vary between more than two
 * possible values.
 * 
 * @param <S> The device state.
 */
public interface AdjustableDevice<S extends DeviceState> extends Device<S> {

    /**
     * Sets the device state to the new value.
     * 
     * @param state The new commanded level.
     */
    void setState(DeviceState state);
}
