package homer.api;

import java.util.UUID;

/**
 * Represents the id used in the controller to uniquely identify devices.
 */
public class DeviceId {

    private final UUID id = UUID.randomUUID();

    /**
     * 
     * @return The device unique id.
     */
    public UUID get() {
        return this.id;
    }

    @Override
    public boolean equals(Object arg0) {
        return this.id.equals(arg0);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public String toString() {
        return this.id.toString();
    }

}
