package homer.controller.scheduler;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

import homer.common.bounds.Bounds;
import homer.common.temperature.Temperature;
import homer.controller.Controller;
import homer.controller.command.Command;
import homer.model.scheduler.ScheduleId;
import homer.model.scheduler.TemperatureScheduler;
import homer.model.scheduler.TimeScheduler;
import homer.model.thermometer.Thermometer;
import homer.view.scheduler.TimeSchedulerView;

/**
 * Implentation of {@link TimeSchedulerController} for the control of
 * {@link Temperature}.
 */
public final class TemperatureSchedulerController implements TimeSchedulerController<Temperature> {

    private static final int FIFTY_NINE = 59;
    private final TimeScheduler<Temperature> scheduler = new TemperatureScheduler();
    private final TimeSchedulerView<Temperature> view;
    private final TemperatureCommands commands;
    private final Controller controller;

    /**
     * Creates a new {@link TemperatureSchedulerController}.
     * 
     * @param view       the scheduler view.
     * @param controller the domotic controller.
     */
    public TemperatureSchedulerController(final TimeSchedulerView<Temperature> view,
            final Controller controller) {
        this.view = Objects.requireNonNull(view);
        this.controller = Objects.requireNonNull(controller);
        this.commands = new TemperatureCommandsImpl();
    }

    @Override
    public void addSchedule(final Bounds<Integer> timeBounds, final Bounds<Temperature> paramBounds) {
        this.scheduler.addSchedule(
                new Bounds<>(LocalTime.of(timeBounds.getLowerBound(), 0),
                        LocalTime.of(timeBounds.getUpperBound() - 1, FIFTY_NINE)),
                paramBounds);
        updateView();
    }

    @Override
    public void removeSchedule(final ScheduleId scheduleId) {
        this.scheduler.removeSchedule(scheduleId);
        updateView();
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        // Run the temperature scheduler check.
        final var clock = this.controller.getClock();
        this.controller.getDeviceManager().getDevices().values().stream()
                .filter(Thermometer.class::isInstance)
                .map(d -> (Thermometer) d)
                .findAny()
                .ifPresent(t -> this.checkSchedules(clock.getDateTime().toLocalTime(),
                        t.getState().getTemperature()));
    }

    /**
     * Checks the schedules with the current time and parameter and executes the
     * necessary actions.
     * 
     * @param currentTime      the current time.
     * @param currentParameter the current parameter.
     */
    private void checkSchedules(final LocalTime currentTime, final Temperature currentParameter) {
        final Optional<Command> chosenCommand = switch (this.scheduler.checkSchedule(currentTime,
                currentParameter)) {
            case ABOVE_BOUNDS -> Optional.of(this.commands.coolCommand());
            case BELOW_BOUNDS -> Optional.of(this.commands.heatCommand());
            case IN_BOUNDS -> Optional.of(this.commands.stopCommand());
            case NOT_FOUND -> Optional.empty();
            default -> throw new IllegalArgumentException();
        };
        if (chosenCommand.isPresent()) {
            this.controller.receiveCommand(chosenCommand.get());
        }
    }

    private void updateView() {
        this.view.updateSchedules(this.scheduler.getSchedules());
    }

}
