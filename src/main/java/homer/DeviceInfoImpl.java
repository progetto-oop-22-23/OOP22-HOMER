package homer;

import homer.api.DeviceId;
import homer.api.DeviceInfo;

/**
 * Implementation of {@link DeviceInfo}.
 */
public class DeviceInfoImpl implements DeviceInfo {
    private final DeviceId id;
    private final String type;

    /**
     * @param id considered final.
     * @param type considered final. 
     */
    public DeviceInfoImpl(final DeviceId id, final String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public final DeviceId getID() {
        return this.id;
    }

    @Override
    public final String getType() {
        return this.type;
    }

}
