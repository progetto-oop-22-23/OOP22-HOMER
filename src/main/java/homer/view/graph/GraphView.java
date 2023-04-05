package homer.view.graph;

import java.util.Set;

import homer.common.history.HistoryData;

/**
 * Represents a graph visualization of historical data.
 * 
 * @param <T> the type of data.
 */
public interface GraphView<T> {

    /**
     * Refreshes the graph view with the supplied historical data.
     * 
     * @param historyData the set of historical data.
     */
    void updateGraph(Set<HistoryData<T>> historyData);

}
