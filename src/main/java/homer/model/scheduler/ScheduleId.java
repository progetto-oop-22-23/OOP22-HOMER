package homer.model.scheduler;

import java.util.UUID;

/**
 * Represents an unique id for a schedule.
 * 
 * @param scheduleId the schedule id.
 */
public record ScheduleId(UUID scheduleId) {

    /**
     * Creates a new {@link ScheduleId} with a random {@link UUID}.
     */
    public ScheduleId() {
        this(UUID.randomUUID());
    }

}
