package homer;

import homer.api.DeviceInfo;

/**
 * Implementation of {@link DeviceInfo}.
 */
public final class DeviceInfoImpl implements DeviceInfo {
    private final long id;
    private final String type;

    /**
     * @param id considered final.
     * @param type considered final. 
     */
    public DeviceInfoImpl(final long id, final String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public long getID() {
        return this.id;
    }

    @Override
    public String getType() {
        return this.type;
    }

}
