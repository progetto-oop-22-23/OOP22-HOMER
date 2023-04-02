package homer.controller.history;

import java.time.Duration;

import homer.common.history.HistoryData;
import homer.common.temperature.Temperature;
import homer.controller.Controller;
import homer.model.history.HistoricalDataLogger;
import homer.model.history.HistoricalDataLoggerImpl;
import homer.model.thermometer.Thermometer;
import homer.view.graph.GraphView;

/**
 * Implementation of {@link HistoricalDataController} for logging of the
 * temperature.
 */
public final class TemperatureLogger implements HistoricalDataController<Temperature> {

    private static final Duration LOG_INTERVAL = Duration.ofHours(1);
    private final HistoricalDataLogger<Temperature> log = new HistoricalDataLoggerImpl<>();
    private final GraphView<Temperature> view;
    private final Controller controller;
    private Duration accumulatedTime = Duration.ofNanos(0);

    /**
     * Creates a new {@link TemperatureLogger}.
     * 
     * @param view       the graph view.
     * @param controller the domotic controller.
     */
    public TemperatureLogger(final GraphView<Temperature> view, final Controller controller) {
        this.view = view;
        this.controller = controller;
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        if (this.accumulatedTime.compareTo(LOG_INTERVAL) >= 0) {
            final var currTime = this.controller.getClock().getDateTime();
            this.controller.getDeviceManager().getDevices().values().stream()
                    .filter(Thermometer.class::isInstance)
                    .map(d -> (Thermometer) d)
                    /*
                     * This solution would not work with multiple thermometers in different
                     * environments. In that case it would be better to choose a particular
                     * thermometer to log, or to have multiple logs for all the different
                     * thermometers.
                     */
                    .findFirst()
                    .ifPresent(t -> this.log.logData(new HistoryData<>(currTime, t.getState().getTemperature())));
            this.view.updateGraph(this.log.getHistory());
            this.accumulatedTime = Duration.ofNanos(0);
        }
        this.accumulatedTime = this.accumulatedTime.plus(deltaTime);
    }

}
