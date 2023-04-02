package homer.common.history;

import java.time.LocalDateTime;

/**
 * Represents some data associated to a datetime.
 * 
 * @param <T> the type of data.
 */
public record HistoryData<T>(LocalDateTime dateTime, T data) implements Comparable<HistoryData<T>> {

    @Override
    public int compareTo(final HistoryData<T> other) {
        return this.dateTime().compareTo(other.dateTime());
    }

}
