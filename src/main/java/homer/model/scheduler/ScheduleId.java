package homer.model.scheduler;

import java.util.UUID;

/**
 * Represents an unique id for a schedule.
 * 
 * @param scheduleId the schedule id.
 */
public record ScheduleId(UUID scheduleId) {
}
