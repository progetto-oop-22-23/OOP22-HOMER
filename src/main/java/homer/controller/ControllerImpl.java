package homer.controller;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.common.time.Clock;
import homer.common.time.ClockImpl;
import homer.controller.command.Command;
import homer.model.airquality.AirqualitySensor;
import homer.model.thermometer.Thermometer;

/**
 * Controller Implementation.
 */
public final class ControllerImpl implements Controller {

    private final List<Command> commands = new LinkedList<>();
    private final DeviceManager deviceManager = new DeviceManagerImpl(this);
    private final ViewManager viewManager = new ViewManagerImpl();
    private final Clock clock = new ClockImpl();

    @Override
    public void receiveCommand(final Command command) {
        if (command != null) {
            commands.add(command);
        }
    }

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a design choice")
    public DeviceManager getDeviceManager() {
        return this.deviceManager;
    }

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a design choice")
    public ViewManager getViewManager() {
        return this.viewManager;
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        this.clock.updateTick(deltaTime);
        deviceManager.getDevices().entrySet().stream()
            .filter(x -> x.getValue() instanceof Thermometer)
            .forEach(x -> viewManager.updateDeviceState(x.getKey(), ((Thermometer) x.getValue()).getState()));
        deviceManager.getDevices().entrySet().stream()
            .filter(x -> x.getValue() instanceof AirqualitySensor)
            .forEach(x -> viewManager.updateDeviceState(x.getKey(), ((AirqualitySensor) x.getValue()).getState()));

        // Run the queued commands.
        for (final var it = this.commands.iterator(); it.hasNext();) {
            final var command = it.next();
            command.execute(this);
            it.remove();
        }
    }

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This is a design choice")
    public Clock getClock() {
        return this.clock;
    }

}
