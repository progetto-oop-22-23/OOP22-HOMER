package homer.core;

import java.time.temporal.TemporalAmount;

/**
 * This interface models an object which is updated in discrete time steps.
 */
public interface DiscreteObject {

    /**
     * Sends the time update event to the object.
     * 
     * @param dt The time that has passed since the last tick.
     */
    void updateTick(TemporalAmount dt);

}
