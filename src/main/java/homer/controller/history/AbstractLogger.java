package homer.controller.history;

import java.time.Duration;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import homer.common.history.HistoryData;
import homer.model.history.HistoricalDataLogger;
import homer.view.graph.GraphView;

public abstract class AbstractLogger<T> implements HistoricalDataController<T> {

    private static final long LIMIT_ENTRIES = 30;
    private static final Duration LOG_INTERVAL = Duration.ofHours(1);
    private final GraphView<T> view;
    private final Supplier<HistoryData<T>> dataSupplier;
    private Duration accumulatedTime = Duration.ofNanos(0);

    protected AbstractLogger(final Supplier<HistoryData<T>> dataSupplier, final GraphView<T> view) {
        this.dataSupplier = dataSupplier;
        this.view = view;
    }

    abstract protected HistoricalDataLogger<T> getLog();

    @Override
    public void updateTick(final Duration deltaTime) {
        if (this.accumulatedTime.compareTo(LOG_INTERVAL) >= 0) {
            getLog().logData(dataSupplier.get());
            final var historyData = getLog().getHistory();
            this.view.updateGraph(historyData.stream()
                    .sorted()
                    .skip(historyData.size() < LIMIT_ENTRIES ? 0 : historyData.size() - LIMIT_ENTRIES)
                    .collect(Collectors.toSet()));
            this.accumulatedTime = Duration.ofNanos(0);
        }
        this.accumulatedTime = this.accumulatedTime.plus(deltaTime);
    }

}
