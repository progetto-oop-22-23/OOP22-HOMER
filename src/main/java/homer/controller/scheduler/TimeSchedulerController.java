package homer.controller.scheduler;

import java.time.LocalTime;
import java.util.UUID;

import homer.common.bounds.Bounds;

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
    void removeSchedule(UUID scheduleId);

}
