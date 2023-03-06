package homer.api;

import java.util.UUID;

/**
 * {@link DeviceId} implementation.
 */
public final class DeviceIdImpl implements DeviceId {

    private final UUID id = UUID.randomUUID();

    /**
     * 
     * @return The device unique id.
     */
    public UUID get() {
        return this.id;
    }

    @Override
    public boolean equals(final Object arg0) {
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
