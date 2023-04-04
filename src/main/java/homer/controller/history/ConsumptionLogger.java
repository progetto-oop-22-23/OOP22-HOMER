package homer.controller.history;

import java.util.Optional;

import homer.common.history.HistoryData;
import homer.common.time.Clock;
import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.view.graph.GraphView;

public final class ConsumptionLogger extends AbstractLogger<Double> {

    protected ConsumptionLogger(final Clock clock, final ElectricalMeter meter, final GraphView<Double> view) {
        super(() -> Optional.of(new HistoryData<>(clock.getDateTime(), meter.getGlobalConsumption())), view);
    }

}
