package homer.controller.scheduler;

import java.time.LocalTime;

import homer.common.bounds.Bounds;
import homer.model.scheduler.ScheduleId;

public interface TimeSchedulerController<T extends Comparable<T>> {

    /**
     * Adds a new schedule.
     * 
     * @param timeBounds
     * @param paramBounds
     */
    void addSchedule(Bounds<LocalTime> timeBounds, Bounds<T> paramBounds);

    /**
     * Removes a schedule.
     * 
     * @param scheduleId
     */
    void removeSchedule(ScheduleId scheduleId);

}
