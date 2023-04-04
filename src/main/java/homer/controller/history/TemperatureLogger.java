package homer.controller.history;

import java.util.Optional;
import homer.common.history.HistoryData;
import homer.common.temperature.Temperature;
import homer.controller.Controller;
import homer.model.thermometer.Thermometer;
import homer.view.graph.GraphView;

/**
 * Implementation of {@link HistoricalDataController} for logging of the
 * temperature.
 */
public final class TemperatureLogger extends AbstractLogger<Temperature> {

    /**
     * Creates a new {@link TemperatureLogger}.
     * 
     * @param view       the graph view.
     * @param controller the domotic controller.
     */
    public TemperatureLogger(final GraphView<Temperature> view, final Controller controller) {
        super(() -> {
            final var currTime = controller.getClock().getDateTime();
            final var thermometer = controller.getDeviceManager().getDevices().values().stream()
                    .filter(Thermometer.class::isInstance)
                    .map(d -> (Thermometer) d)
                    /*
                     * This solution would not work with multiple thermometers in different
                     * environments. In that case it would be better to choose a particular
                     * thermometer to log, or to have multiple logs for all the different
                     * thermometers.
                     */
                    .findFirst();
            return thermometer.isPresent()
                    ? Optional.of(new HistoryData<>(currTime, thermometer.get().getState().getTemperature()))
                    : Optional.empty();
        }, view);
    }

}
