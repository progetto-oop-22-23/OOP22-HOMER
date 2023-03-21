package homer.controller.command;

import java.util.Optional;

/**
 * Disconnects all devices from the controller.
 */
public final class DisconnectAllDevices implements Command {

    @Override
    public Optional<Object> getNewState() {
        return Optional.empty();
    }

}
