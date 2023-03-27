package homer.api;

/**
 * Interface for the device visual representation.
 * 
 * @param <S> The device state.
 */
public interface DeviceView {

    /**
     * 
     * @return Device information to be displayed visually.
     */
    DeviceState getState();

    /**
     * 
     * @param state the new state for the device's parameter
     */
    void setState(DeviceState state);

}
