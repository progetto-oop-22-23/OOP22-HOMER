package homer.controller.history;

import java.util.Optional;
import homer.common.history.HistoryData;
import homer.controller.Controller;
import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirqualitySensor;
import homer.view.graph.GraphView;

/**
 * Implementation of historical data controller for air quality.
 */
public final class AirQualityLogger extends AbstractLogger<AirQualityState> {

    /**
     * Creates a new {@link AirQualityLogger}.
     * 
     * @param view       the graph view.
     * @param controller the domotic controller.
     */
    public AirQualityLogger(final GraphView<AirQualityState> view, final Controller controller) {
        super(view, () -> {
            final var currTime = controller.getClock().getDateTime();
            final var sensor = controller.getDeviceManager().getDevices().values().stream()
                    .filter(AirqualitySensor.class::isInstance)
                    .map(d -> (AirqualitySensor) d)
                    .findFirst();
            return sensor.isPresent()
                    ? Optional.of(new HistoryData<>(currTime, sensor.get().getState()))
                    : Optional.empty();
        });
    }

}
