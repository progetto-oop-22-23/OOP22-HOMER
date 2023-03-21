package homer.api;

/**
 * Interface for the {@link Device} technical information reported to the
 * {@link Controller}.
 */
public interface DeviceInfo {

    /**
     * 
     * @return An unique identifier of the device.
     */
    DeviceId getID();

    /**
     * 
     * @return The type of the device eg. Light, Door, Lock.
     */
    DeviceType getType();

}
