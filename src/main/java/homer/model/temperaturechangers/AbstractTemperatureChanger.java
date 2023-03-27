package homer.model.temperaturechangers;

import homer.api.DeviceState;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.environment.Environment;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Uses template method on updateTick. At a default intensity, it changes the
 * temperature by
 * 1 celsius degree each hour. By default, minimum temperature is the absolute
 * 0, and the maximum temperature
 * is not set.
 */
public abstract class AbstractTemperatureChanger implements TemperatureChanger {

    private final double maxIntensity;
    private final double minIntensity;
    private double intensity;
    private final Environment environment;
    private Temperature minTemperature = TemperatureFactory.fromKelvin(0);
    private Optional<Temperature> maxTemperature = Optional.empty();

    /**
     * @param minIntensity
     * @param maxIntensity
     * @param environment
     * @param info
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Updating a reference is better than reallocating objects on the heap")
    public AbstractTemperatureChanger(final double minIntensity, final double maxIntensity,
            final Environment environment) {
        this.minIntensity = minIntensity;
        this.maxIntensity = maxIntensity;
        this.intensity = minIntensity;
        this.environment = environment;
    }

    @Override
    public final Double getState() {
        return this.intensity;
    }

    /**
     * @return maximum temperature allowed
     */
    public final Optional<Temperature> getMaxTemperature() {
        return this.maxTemperature;
    }

    @Override
    public final void setMaxTemperature(final Temperature temperature) {
        this.maxTemperature = Optional.of(temperature);
    }

    /**
     * @return minimum temperature allowed
     */
    public final Temperature getMinTemperature() {
        return this.minTemperature;
    }

    @Override
    public final void setMinTemperature(final Temperature temperature) {
        this.minTemperature = temperature;
    }

    /**
     * 
     * @return the environment associated with the object
     */
    protected final Environment getEnvironment() {
        return this.environment;
    }

    @Override
    public void setState(DeviceState state) {
        if (state instanceof TemperatureChangerState) {
            TemperatureChangerState temperatureChangerState =  (TemperatureChangerState) state;
            if (temperatureChangerState.getMaxIntensity().isPresent()) {
                this.intensity = temperatureChangerState.getMaxIntensity().get();
            }
        }
    }

}
