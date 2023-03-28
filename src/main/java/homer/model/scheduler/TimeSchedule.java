package homer.model.scheduler;

import java.time.LocalTime;

import homer.common.bounds.Bounds;

/**
 * Represents a constraint on a certain parameter that should
 * be met in a certain time interval.
 * 
 * @param timeBounds  the time interval.
 * @param paramBounds the target parameter interval.
 * @param <T>         The parameter that should meet the constraint.
 */
public record TimeSchedule<T extends Comparable<T>>(Bounds<LocalTime> timeBounds, Bounds<T> paramBounds) {
}
