package homer.model.environment;

/**
 * The environment represents a physical location in the simulation, with its
 * atmospheric parameters.
 */
public interface Environment {

    /**
     * 
     * @return The current temperature of the environment
     */
    double getTemperature();

    /**
     * Sets the environment temperature to the new value.
     * 
     * @param temperature The new temperature
     */
    void setTemperature(double temperature);

}
