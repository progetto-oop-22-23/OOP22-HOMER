package homer.controller.scheduler;

import java.time.LocalTime;

import homer.common.bounds.Bounds;
import homer.model.scheduler.ScheduleId;
import homer.view.scheduler.TimeSchedulerView;

public interface TimeSchedulerController<T extends Comparable<T>> {

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
     * Sets the observable view.
     * 
     * @param view the observable view.
     */
    void setView(TimeSchedulerView<T> view);

}
