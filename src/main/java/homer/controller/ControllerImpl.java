package homer.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import homer.api.DeviceView;
import homer.controller.command.Command;
import homer.model.environment.Environment;

/**
 * Controller Implementation.
 */
public final class ControllerImpl implements Controller {

    private final List<Command> commands = new LinkedList<>();
    private final DeviceManager deviceManager; 
    private final ViewManager viewManager;

    public ControllerImpl(Environment environment) {
        this.deviceManager = new DeviceManagerImpl(environment);
        this.viewManager = new ViewManagerImpl();
    }

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
