package homer.api;

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
    String getType();

}
