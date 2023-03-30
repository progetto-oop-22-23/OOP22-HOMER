package homer.controller.scheduler;

import homer.controller.Controller;
import homer.model.scheduler.SchedulerCommand;

/**
 * Implementation of {@link TemperatureCommands}.
 */
public final class TemperatureCommandsImpl implements TemperatureCommands {

    private final Controller controller;

    /**
     * Creates a new {@link TemperatureCommandsImpl}.
     * 
     * @param controller the controller.
     */
    public TemperatureCommandsImpl(final Controller controller) {
        this.controller = controller;
    }

    @Override
    public SchedulerCommand heatCommand() {
        return new TemperatureCommand(this.controller, h -> h.getMaxIntensity().get(), c -> c.getMinIntensity().get());
    }

    @Override
    public SchedulerCommand stopCommand() {
        return new TemperatureCommand(this.controller, h -> h.getMinIntensity().get(), c -> c.getMinIntensity().get());
    }

    @Override
    public SchedulerCommand coolCommand() {
        return new TemperatureCommand(this.controller, h -> h.getMinIntensity().get(), c -> c.getMaxIntensity().get());
    }

}
