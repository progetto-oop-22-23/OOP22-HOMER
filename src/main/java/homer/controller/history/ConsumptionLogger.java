package homer.controller.history;

import java.util.Optional;

import homer.common.history.HistoryData;
import homer.common.time.Clock;
import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.view.graph.GraphView;

/**
 * Implementation of {@link HistoricalDataController} for logging of the
 * global energy consumption.
 */
public final class ConsumptionLogger extends AbstractLogger<Double> {

    /**
     * Creates a new {@link ConsumptionLogger}.
     * 
     * @param clock the controller's clock.
     * @param meter the electrical meter.
     * @param view  the graph view.
     */
    protected ConsumptionLogger(final Clock clock, final ElectricalMeter meter, final GraphView<Double> view) {
        super(() -> Optional.of(new HistoryData<>(clock.getDateTime(), meter.getGlobalConsumption())), view);
    }

}
