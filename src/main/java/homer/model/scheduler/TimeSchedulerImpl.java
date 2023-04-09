package homer.model.scheduler;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import homer.common.bounds.Bounds;

/**
 * Implementation of a {@link TimeScheduler}.
 * 
 * @param <T> the type of data to target for the schedule.
 */
public final class TimeSchedulerImpl<T extends Comparable<T>> implements TimeScheduler<T> {

    private final Map<ScheduleId, TimeSchedule<T>> schedules = new HashMap<>();

    @Override
    public void addSchedule(final Bounds<LocalTime> timeBounds, final Bounds<T> paramBounds) {
        if (!isOverlapsing(timeBounds)) {
            this.schedules.put(new ScheduleId(), new TimeSchedule<>(timeBounds, paramBounds));
        } else {
            throw new IllegalArgumentException("selected time range is colliding with another existing schedule!");
        }
    }

    @Override
    public void removeSchedule(final ScheduleId scheduleId) {
        this.schedules.remove(scheduleId);
    }

    @Override
    public Map<ScheduleId, TimeSchedule<T>> getSchedules() {
        return Map.copyOf(this.schedules);
    }

    @Override
    public ParameterResult checkSchedule(final LocalTime currentTime, final T currentParameter) {
        final var targetBounds = this.schedules.values().stream()
                .filter(s -> isTimeWithinBounds(currentTime, s.timeBounds()))
                .map(TimeSchedule::paramBounds)
                .findFirst();
        if (targetBounds.isEmpty()) {
            return ParameterResult.NOT_FOUND;
        } else if (currentParameter.compareTo(targetBounds.get().getLowerBound()) < 0) {
            return ParameterResult.BELOW_BOUNDS;
        } else if (currentParameter.compareTo(targetBounds.get().getUpperBound()) > 0) {
            return ParameterResult.ABOVE_BOUNDS;
        } else {
            return ParameterResult.IN_BOUNDS;
        }
    }

    private boolean isOverlapsing(final Bounds<LocalTime> timeBounds) {
        final var newTimeStart = timeBounds.getLowerBound();
        final var newTimeEnd = timeBounds.getUpperBound();
        return this.schedules.values().stream()
                .map(TimeSchedule::timeBounds)
                .filter(tb -> areBoundsWithinBounds(timeBounds, tb) || areBoundsWithinBounds(tb, timeBounds)
                        || isTimeWithinBounds(newTimeStart, tb) || isTimeWithinBounds(newTimeEnd, tb))
                .count() > 0;
    }

    // checks if boundsA are within or coincident with boundsB
    private boolean areBoundsWithinBounds(final Bounds<LocalTime> boundsA, final Bounds<LocalTime> boundsB) {
        return boundsA.getLowerBound().compareTo(boundsB.getLowerBound()) >= 0
                && boundsA.getUpperBound().compareTo(boundsB.getUpperBound()) <= 0;
    }

    private boolean isTimeWithinBounds(final LocalTime time, final Bounds<LocalTime> timeBounds) {
        return time.compareTo(timeBounds.getLowerBound()) >= 0
                && time.compareTo(timeBounds.getUpperBound()) <= 0;
    }
}
