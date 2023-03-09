package homer.common.time;

import java.time.LocalDateTime;
import homer.core.DiscreteObject;

/**
 * This interface models a clock which reports local date and time and can be
 * updated with ticks of variable time difference.
 */
public interface Clock extends DiscreteObject {

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

}
