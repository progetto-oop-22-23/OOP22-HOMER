package homer.api;

import java.util.UUID;

/**
 * Represents the id used in the controller to uniquely identify devices.
 */
public interface DeviceId {

    /**
     * 
     * @return The device unique id.
     */
    UUID get();

}
