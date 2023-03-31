package homer.controller;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import homer.common.temperature.Temperature;
import homer.common.time.Clock;
import homer.common.time.ClockImpl;
import homer.controller.command.Command;
import homer.controller.scheduler.TemperatureSchedulerController;
import homer.controller.scheduler.TimeSchedulerController;
import homer.model.thermometer.Thermometer;
import homer.view.scheduler.TimeSchedulerView;

/**
 * Controller Implementation.
 */
public final class ControllerImpl implements Controller {

    private final List<Command> commands = new LinkedList<>();
    private final DeviceManager deviceManager = new DeviceManagerImpl(this);
    private final ViewManager viewManager = new ViewManagerImpl();
    private final Clock clock = new ClockImpl();
    private final TimeSchedulerController<Temperature> tempScheduler;

    /**
     * Creates a new {@link ControllerImpl}.
     * 
     * @param schedulerView the view of the scheduler.
     */
    public ControllerImpl(final TimeSchedulerView<Temperature> schedulerView) {
        this.tempScheduler = new TemperatureSchedulerController(schedulerView, this);
        schedulerView.setScheduler(this.tempScheduler);
    }

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

        // Run the queued commands.
        for (final var it = this.commands.iterator(); it.hasNext();) {
            final var command = it.next();
            command.execute(this);
            it.remove();
        }

        // Run the temperature scheduler check.
        getDeviceManager().getDevices().values().stream()
                .filter(Thermometer.class::isInstance)
                .map(d -> (Thermometer) d)
                .findAny()
                .ifPresent(t -> this.tempScheduler.checkSchedules(this.clock.getDateTime().toLocalTime(),
                        t.getState().getTemperature()));
    }

    @Override
    public Clock getClock() {
        return this.clock;
    }

}
