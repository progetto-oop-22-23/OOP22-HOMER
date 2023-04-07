package homer.controller.scheduler;

import homer.controller.command.Command;

/**
 * Implementation of {@link TemperatureCommands}.
 */
public final class TemperatureCommandsImpl implements TemperatureCommands {

    @Override
    public Command heatCommand() {
        return new TemperatureCommand(h -> h.getMaxIntensity().get(), c -> c.getMinIntensity().get());
    }

    @Override
    public Command stopCommand() {
        return new TemperatureCommand(h -> h.getMinIntensity().get(), c -> c.getMinIntensity().get());
    }

    @Override
    public Command coolCommand() {
        return new TemperatureCommand(h -> h.getMinIntensity().get(), c -> c.getMaxIntensity().get());
    }

}
