package homer.controller.command;

import java.util.Optional;

import homer.api.DeviceId;

/**
 * Disconnect the device that has {@link DeviceId} id from the contoller.
 */
public final class DisconnectDevice implements Command {

    private final DeviceId deviceId;

    /**
     * 
     * @param deviceId the id of the device that should be disconnected
     */
    public DisconnectDevice(final DeviceId deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public Optional<Object> getNewState() {
        return Optional.of(this.deviceId);
    }

}
