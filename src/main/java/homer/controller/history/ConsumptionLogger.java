package homer.controller.history;

import java.util.function.Supplier;

import homer.common.history.HistoryData;
import homer.model.history.HistoricalDataLogger;
import homer.view.graph.GraphView;

public final class ConsumptionLogger extends AbstractLogger<Double> {

    protected ConsumptionLogger(Supplier<HistoryData<Double>> dataSupplier, GraphView<Double> view) {
        super(dataSupplier, view);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected HistoricalDataLogger<Double> getLog() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLog'");
    }

}
