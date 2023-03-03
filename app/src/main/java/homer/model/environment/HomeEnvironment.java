package homer.model.environment;

/**
 * {@link Environment} implementation by modelling the home environment as one
 * block with no consideration of separate rooms.
 */
public final class HomeEnvironment implements Environment {

    private double temperature;

    /**
     * 
     * @param temperature The initial temperature of the environment
     */
    public HomeEnvironment(final double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double getTemperature() {
        return this.temperature;
    }

    @Override
    public void setTemperature(final double temperature) {
        this.temperature = temperature;
    }

}
