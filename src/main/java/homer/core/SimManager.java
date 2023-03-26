package homer.core;

import java.time.Duration;

/**
 * Interface for the control of the simulation.
 */
public interface SimManager {

    /**
     * Gets the simulation step period.
     * 
     * @return the simulation step period.
     */
    Duration getSimStepPeriod();

}
