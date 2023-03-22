package homer.model.scheduler;

import java.util.Set;
import java.time.LocalTime;

/**
 * This interface models a device which assures that certain constraints on
 * certain parameters are met in a certain interval of time.
 * 
 * @param <T> The parameter that should meet the constraints.
 */
public interface TimeScheduler<T> {

    /**
     * Adds a new schedule.
     * 
     * @param schedule The schedule to add.
     */
    void addSchedule(TimeSchedule<T> schedule);

    /**
     * Removes a schedule.
     * 
     * @param schedule the schedule to remove.
     */
    void removeSchedule(TimeSchedule<T> schedule);

    /**
     * Returns the added schedules.
     * 
     * @return the added schedules.
     */
    Set<TimeSchedule<T>> getSchedules();

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
