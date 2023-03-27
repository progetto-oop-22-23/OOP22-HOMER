package homer.view.scheduler;

import java.util.Map;
import java.util.UUID;

import homer.controller.scheduler.TimeSchedulerController;
import homer.model.scheduler.TimeSchedule;

public interface TimeSchedulerView<T extends Comparable<T>> {

    void updateSchedules(Map<UUID, TimeSchedule<T>> schedules);

    void removeSchedule(UUID scheduleId);

    void setScheduler(TimeSchedulerController<T> scheduler);

}
