package homer.controller.command.createdevicecommand;

import homer.controller.Controller;
import homer.model.lock.SimpleLock;

/**
 * Implementation of {@link CreateDeviceCommand} for {@link SimpleLock}.
 */
public final class CreateLock implements CreateDeviceCommand {

    private static final String NAME = "Add Lock";

    @Override
    public void execute(final Controller controller) {
        controller.getDeviceManager().addDevice(new SimpleLock());
    }

    @Override
    public String getStringRep() {
        return NAME;
    }

}
