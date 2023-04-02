package homer.controller;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import homer.common.temperature.TemperatureFactory;
import homer.common.time.Clock;
import homer.common.time.ClockImpl;
import homer.controller.command.Command;
import homer.model.airquality.AirQualityStateFactory;
import homer.model.environment.HomeEnvironment;

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
        final var temp = TemperatureFactory.fromCelsius(0);
        final var airquality =  AirQualityStateFactory.normalAirQuality();
        this.viewManager.updateEnvironment(new HomeEnvironment(temp, airquality));

        // Run the queued commands.
        for (final var it = this.commands.iterator(); it.hasNext();) {
            final var command = it.next();
            command.execute(this);
            it.remove();
        }
    }

    @Override
    public Clock getClock() {
        return this.clock;
    }

}
