package homer.controller.scheduler;

import java.time.LocalTime;
import java.util.Optional;

import homer.common.bounds.Bounds;
import homer.common.temperature.Temperature;
import homer.controller.Controller;
import homer.controller.command.Command;
import homer.model.scheduler.ScheduleId;
import homer.model.scheduler.TemperatureScheduler;
import homer.model.scheduler.TimeScheduler;
import homer.view.scheduler.TimeSchedulerView;

public final class TemperatureSchedulerController implements TimeSchedulerController<Temperature> {

    private final TimeScheduler<Temperature> scheduler = new TemperatureScheduler();
    private final TimeSchedulerView<Temperature> view;
    private final TemperatureCommands commands;
    private final Controller controller;

    public TemperatureSchedulerController(final TimeSchedulerView<Temperature> view,
            final Controller controller) {
        this.view = view;
        this.controller = controller;
        this.commands = new TemperatureCommandsImpl();
    }

    @Override
    public void addSchedule(final Bounds<LocalTime> timeBounds, final Bounds<Temperature> paramBounds) {
        this.scheduler.addSchedule(timeBounds, paramBounds);
        updateView();
    }

    @Override
    public void removeSchedule(final ScheduleId scheduleId) {
        this.scheduler.removeSchedule(scheduleId);
        updateView();
    }

    @Override
    public void checkSchedules(final LocalTime currentTime, final Temperature currentParameter) {
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
