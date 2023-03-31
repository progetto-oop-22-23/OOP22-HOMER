package homer.controller.scheduler;

import java.util.function.Function;

import homer.controller.Controller;
import homer.controller.command.Command;
import homer.model.temperaturechangers.AirConditioning;
import homer.model.temperaturechangers.Heating;
import homer.model.temperaturechangers.TemperatureChanger;
import homer.model.temperaturechangers.TemperatureChangerState;

/**
 * Implementation of a {@link SchedulerCommand} for the control of the
 * temperature.
 */
public final class TemperatureCommand implements Command {

    private final Function<TemperatureChangerState, Double> heating;
    private final Function<TemperatureChangerState, Double> cooling;

    /**
     * Creates a new {@link TemperatureCommand}.
     * 
     * @param heating the function to decide the intensity for heating.
     * @param cooling the function to decide the intensity for cooling.
     */
    public TemperatureCommand(final Function<TemperatureChangerState, Double> heating,
            final Function<TemperatureChangerState, Double> cooling) {
        this.heating = heating;
        this.cooling = cooling;
    }

    @Override
    public void execute(final Controller controller) {
        setState(controller, Heating.class, heating);
        setState(controller, AirConditioning.class, cooling);
    }

    private <T extends TemperatureChanger> void setState(final Controller controller, final Class<T> deviceType,
            final Function<TemperatureChangerState, Double> deviceFunction) {
        controller.getDeviceManager().getDevices().entrySet().stream()
                .filter(e -> deviceType.isInstance(e.getValue()))
                .forEach(e -> {
                    final var tc = ((TemperatureChanger) e.getValue());
                    final var newIntensity = deviceFunction.apply(tc.getState());
                    controller.getDeviceManager().updateDeviceState(e.getKey(),
                            new TemperatureChangerState().addCurrentIntensity(newIntensity));
                });
    }

}
