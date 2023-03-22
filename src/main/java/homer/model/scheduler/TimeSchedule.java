package homer.model.scheduler;

import java.time.LocalTime;

/**
 * This interface models a set of constraints on a certain parameter that should
 * be met in a certain time interval.
 * 
 * @param <T> The parameter that should meet the constraints.
 */
public interface TimeSchedule<T> {

    /**
     * Checks if the constraints are met, and if not, executes the necessary
     * actions.
     * 
     * @param currentTime      The current 24h time.
     * @param currentParameter The current parameter to check against the
     *                         constraints.
     */
    void checkSchedule(LocalTime currentTime, T currentParameter);

}
