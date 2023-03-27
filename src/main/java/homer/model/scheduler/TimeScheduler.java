package homer.model.scheduler;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.time.LocalTime;

/**
 * This interface models a component which assures that certain constraints on
 * certain parameters are met in a certain interval of time.
 * 
 * @param <T> The parameter that should meet the constraints.
 */
public interface TimeScheduler<T extends Comparable<T>> {

    /**
     * Returns the added schedules.
     * 
     * @return the added schedules.
     */
    Map<UUID, TimeSchedule<T>> getSchedules();

    /**
     * Checks if the constraints are met, and if not, executes the necessary
     * actions.
     * 
     * @param currentTime      The current 24h clock time.
     * @param currentParameter The current parameter to check against the
     *                         constraints.
     */
    void checkSchedule(LocalTime currentTime, T currentParameter);

}
