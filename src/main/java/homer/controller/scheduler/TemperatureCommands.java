package homer.controller.scheduler;

import homer.model.scheduler.SchedulerCommand;

public record TemperatureCommands(SchedulerCommand heatCommand, SchedulerCommand stopCommand,
        SchedulerCommand coolCommand) {
}
