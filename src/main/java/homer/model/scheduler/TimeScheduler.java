package homer.model.scheduler;

import java.util.Map;

import homer.common.bounds.Bounds;

import java.time.LocalTime;

/**
 * This interface models a component which assures that certain constraints on
 * certain parameters are met in a certain interval of time.
 * 
 * @param <T> The parameter that should meet the constraints.
 */
public interface TimeScheduler<T extends Comparable<T>> {

    /**
     * Indicates where the current parameter stands when checked against the
     * schedule, or whether no applicable schedule was found.
     */
    enum ParameterResult {
        /**
         * Parameter is below the target bounds.
         */
        BELOW_BOUNDS,
        /**
         * Parameter is within the target bounds.
         */
        IN_BOUNDS,
        /**
         * Parameter is above the target bounds.
         */
        ABOVE_BOUNDS,
        /**
         * No applicable schedule was found.
         */
        NOT_FOUND;
    }

    /**
     * Adds a new schedule.
     * 
     * @param timeBounds  the time bounds for the schedule.
     * @param paramBounds the target parameter bounds for the schedule.
     */
    void addSchedule(Bounds<LocalTime> timeBounds, Bounds<T> paramBounds);

    /**
     * Removes a schedule.
     * 
     * @param scheduleId the id of the schedule to remove.
     */
    void removeSchedule(ScheduleId scheduleId);

    /**
     * Returns the added schedules.
     * 
     * @return the added schedules.
     */
    Map<ScheduleId, TimeSchedule<T>> getSchedules();

    /**
     * Checks if the constraints are met, and returns whether the parameter is
     * below/above/within bounds or if no schedule exists for the current time.
     * 
     * @param currentTime      The current 24h clock time.
     * @param currentParameter The current parameter to check against the
     *                         constraints.
     * @return the state of the {@code currentParameter} with regards to the
     *         schedules.
     */
    ParameterResult checkSchedule(LocalTime currentTime, T currentParameter);

}
