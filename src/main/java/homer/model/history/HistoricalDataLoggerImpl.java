package homer.model.history;

import java.util.HashSet;
import java.util.Set;

import homer.common.history.HistoryData;

/**
 * Generic implementation of HistoricalDataLogger.
 * 
 * @param <T> the type of data to store.
 */
public final class HistoricalDataLoggerImpl<T> implements HistoricalDataLogger<T> {

    private final Set<HistoryData<T>> historyData = new HashSet<>();

    @Override
    public void logData(final HistoryData<T> historyData) {
        this.historyData.add(historyData);
    }

    @Override
    public Set<HistoryData<T>> getHistory() {
        return Set.copyOf(this.historyData);
    }

}
