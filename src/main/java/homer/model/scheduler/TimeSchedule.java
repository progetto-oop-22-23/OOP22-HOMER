package homer.model.scheduler;

import java.time.LocalTime;

import homer.common.bounds.Bounds;

/**
 * This interface models a constraint on a certain parameter that should
 * be met in a certain time interval.
 * 
 * @param <T> The parameter that should meet the constraint.
 */
public interface TimeSchedule<T extends Comparable<T>> {

    Bounds<LocalTime> getTimeBounds();

    Bounds<T> getParaBounds();

}
