package homer.controller.history;

import java.util.Optional;
import homer.common.history.HistoryData;
import homer.controller.Controller;
import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirqualitySensor;
import homer.view.graph.GraphView;

public final class AirQualityLogger extends AbstractLogger<AirQualityState> {

    public AirQualityLogger(final Controller controller,
            final GraphView<AirQualityState> view) {
        super(() -> {
            final var currTime = controller.getClock().getDateTime();
            final var sensor = controller.getDeviceManager().getDevices().values().stream()
                    .filter(AirqualitySensor.class::isInstance)
                    .map(d -> (AirqualitySensor) d)
                    .findFirst();
            return sensor.isPresent()
                    ? Optional.of(new HistoryData<>(currTime, sensor.get().getState()))
                    : Optional.empty();
        }, view);
    }

}
