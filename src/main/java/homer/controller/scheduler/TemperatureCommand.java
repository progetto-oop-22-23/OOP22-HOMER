package homer.controller.scheduler;

import java.util.function.Function;

import homer.controller.Controller;
import homer.model.scheduler.SchedulerCommand;
import homer.model.temperaturechangers.AirConditioning;
import homer.model.temperaturechangers.Heating;
import homer.model.temperaturechangers.TemperatureChanger;
import homer.model.temperaturechangers.TemperatureChangerState;

/**
 * Implementation of a {@link SchedulerCommand} for the control of the
 * temperature.
 */
public final class TemperatureCommand implements SchedulerCommand {

    private final Controller controller;
    private final Function<TemperatureChangerState, Double> heating;
    private final Function<TemperatureChangerState, Double> cooling;

    /**
     * Creates a new {@link TemperatureCommand}
     * 
     * @param controller the controller.
     * @param heating    the function to decide the intensity for heating.
     * @param cooling    the function to decide the intensity for cooling.
     */
    public TemperatureCommand(final Controller controller,
            final Function<TemperatureChangerState, Double> heating,
            final Function<TemperatureChangerState, Double> cooling) {
        this.controller = controller;
        this.heating = heating;
        this.cooling = cooling;
    }

    @Override
    public void execute() {
        setState(Heating.class, heating);
        setState(AirConditioning.class, cooling);
    }

    private <T extends TemperatureChanger> void setState(Class<T> deviceType,
            final Function<TemperatureChangerState, Double> deviceFunction) {
        this.controller.getDeviceManager().getDevices().values().stream()
                .filter(deviceType::isInstance)
                .forEach(d -> {
                    final var h = ((TemperatureChanger) d);
                    final var newIntensity = deviceFunction.apply(h.getState());
                    h.setState(new TemperatureChangerState().addCurrentIntensity(newIntensity));
                });
    }

}
