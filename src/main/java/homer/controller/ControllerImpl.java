package homer.controller;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import homer.common.time.Clock;
import homer.common.time.ClockImpl;
import homer.controller.command.Command;

/**
 * Controller Implementation.
 */
public final class ControllerImpl implements Controller {

    private final List<Command> commands = new LinkedList<>();
    private final DeviceManager deviceManager = new DeviceManagerImpl();
    private final ViewManager viewManager = new ViewManagerImpl();
    private final Clock clock = new ClockImpl();

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

    @Override
    public void updateTick(final Duration deltaTime) {
        this.clock.updateTick(deltaTime);
        System.out.println("Hello from controller " + deltaTime + " Time: " + clock.getDateTime());
    }

}
