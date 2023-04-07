package homer.model.history;

import java.util.Set;

import homer.common.history.HistoryData;

/**
 * Represents a storage of historical data.
 * There is no particular indication for how long data should be retained.
 * 
 * @param <T> the type of data to store.
 */
public interface HistoricalDataLogger<T> {

    /**
     * Adds new historical data.
     * 
     * @param historyData the new historical data.
     */
    void logData(HistoryData<T> historyData);

    /**
     * Returns the stored historical data.
     * 
     * @return the stored historical data.
     */
    Set<HistoryData<T>> getHistory();

}
