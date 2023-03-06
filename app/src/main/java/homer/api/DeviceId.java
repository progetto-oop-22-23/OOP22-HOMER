package homer.api;

import java.util.UUID;

/**
 * Represents the id used in the controller to uniquely identify devices.
 */
public interface DeviceId {

    /**
     * Returns the device unique id.
     * 
     * @return The device unique id.
     */
    UUID get();

}
