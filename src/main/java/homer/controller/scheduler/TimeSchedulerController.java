package homer.controller.scheduler;

import java.time.LocalTime;

import homer.common.bounds.Bounds;
import homer.core.DiscreteObject;
import homer.model.scheduler.ScheduleId;

/**
 * This interface models the controller of a time-based scheduler.
 * 
 * @param <T> the type of the target parameter.
 */
public interface TimeSchedulerController<T extends Comparable<T>> extends DiscreteObject {

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

}
