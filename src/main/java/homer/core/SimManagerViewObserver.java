package homer.core;

import java.time.Duration;

public interface SimManagerViewObserver extends SimManager {

    /**
     * Resumes the simulation.
     */
    void resume();

    /**
     * Pauses the simulation.
     */
    void pause();

    /**
     * Sets the simulation step period.
     * 
     * @param simStepTime the simulation step period.
     */
    void setSimStepPeriod(Duration simStepTime);

}