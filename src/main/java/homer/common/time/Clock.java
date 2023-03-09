package homer.common.time;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

/**
 * This interface models a clock which reports local date and time and can be
 * updated with ticks of variable time difference.
 */
public interface Clock {

    /**
     * Returns the clock's current local date and time.
     * 
     * @return The current local date and time of the clock.
     */
    LocalDateTime getDateTime();

    /**
     * Sets the clock to a new local date and time.
     * 
     * @param dateTime The new local date and time to set.
     */
    void setDateTime(LocalDateTime dateTime);

    /**
     * Updates the clock local date and time with a delta time amount.
     * 
     * @param deltaTime How much time should be added to the current clock time.
     */
    void updateTick(TemporalAmount deltaTime);

}
