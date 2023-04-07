package homer.core;

/**
 * Interface for the control of the simulation from the view.
 */
public interface SimManagerViewObserver {

    /**
     * Resumes the simulation.
     */
    void resume();

    /**
     * Pauses the simulation.
     */
    void pause();

    /**
     * Sets the simulation time rate.
     * 
     * @param timeRate the simulation time rate.
     */
    void setTimeRate(long timeRate);

}
