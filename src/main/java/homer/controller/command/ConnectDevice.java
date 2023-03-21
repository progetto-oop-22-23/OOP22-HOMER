package homer.controller.command;

import java.util.Optional;

/**
 * Still WIP.
 */
public final class ConnectDevice implements Command {

    private final String deviceType;

    /**
     * 
     * @param deviceType the device type that must be created.
     */
    public ConnectDevice(final String deviceType) {
        this.deviceType = deviceType;
    }

    @Override
    public Optional<Object> getNewState() {
        return Optional.of(deviceType);
    }

}
