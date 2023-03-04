package homer.model.environment;

import homer.common.Temperature;

/**
 * {@link Environment} implementation by modelling the home as one block with no
 * consideration of separate rooms.
 */
public final class HomeEnvironment implements Environment {

    private Temperature temperature;

    /**
     * Creates a new home environment.
     * 
     * @param temperature The initial temperature of the environment
     */
    public HomeEnvironment(final Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public Temperature getTemperature() {
        return this.temperature;
    }

    @Override
    public void setTemperature(final Temperature temperature) {
        this.temperature = temperature;
    }

}
