package homer.api;

/**
 * Interface for the device visual representation.
 * 
 */
public interface DeviceView {

    /**
     * 
     * @param state the new state for the device's parameter
     */
    void setState(DeviceState state);

}
