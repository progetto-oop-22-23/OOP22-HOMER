package homer;

import homer.api.DeviceId;
import homer.api.DeviceInfo;
import homer.api.DeviceType;

/**
 * Implementation of {@link DeviceInfo}.
 */
public final class DeviceInfoImpl implements DeviceInfo {
    private final DeviceId id;
    private DeviceType type;

    /**
     * @param id considered final.
     * @param type considered final. 
     */
    public DeviceInfoImpl(final DeviceId id, final DeviceType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public DeviceId getID() {
        return this.id;
    }

    @Override
    public DeviceType getType() {
        return this.type;
    }

}
