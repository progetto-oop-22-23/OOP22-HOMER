package homer.core;

/**
 * Interface for the simulation core management.
 */
public interface SimManager {

    /**
     * Adds an object which should be updated in the loop.
     * 
     * @param observer an object which should be updated in the loop.
     */
    void addObserver(DiscreteObject observer);

    /**
     * Shutdown the simulation.
     */
    void shutdown();

}
