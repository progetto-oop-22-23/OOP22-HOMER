package homer.core;

import java.time.Duration;

/**
 * Interface for the retrieval of the simulation information.
 */
public interface SimManager {

    /**
     * Gets the simulation step period.
     * 
     * @return the simulation step period.
     */
    Duration getSimStepPeriod();

}
