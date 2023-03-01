package homer.api;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public interface Clock {

    /**
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
