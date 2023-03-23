package homer.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import homer.api.DeviceView;
import homer.controller.command.Command;

/**
 * Controller Implementation.
 */
public final class ControllerImpl implements Controller {

    private final List<Command> commands = new LinkedList<>();
    private final DeviceManager deviceManager = new DeviceManagerImpl();
    private final ViewManager viewManager = new ViewManagerImpl();

    @Override
    public Set<DeviceView<?>> getDevices() {
        throw new UnsupportedOperationException("Unimplemented method 'getDevices'");
    }

    @Override
    public void receiveCommand(final Command command) {
        commands.add(command);
    }

    @Override
    public DeviceManager getDeviceManager() {
        return this.deviceManager;
    }

    @Override
    public ViewManager getViewManager() {
        return this.viewManager;
    }

}
