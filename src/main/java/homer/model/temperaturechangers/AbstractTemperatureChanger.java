package homer.model.temperaturechangers;

import homer.api.DeviceInfo;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.model.environment.Environment;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;


/**
 * Uses template method on updateTick. At a default intensity, it changes the temperature by 
 * 1 celsius degree each hour. By default, minimum temperature is the absolute 0, and the maximum temperature
 * is not set.
 */
public abstract class AbstractTemperatureChanger implements TemperatureChanger {

    private final double maxIntensity;
    private final double minIntensity;
    private double intensity;
    private final Environment environment;
    private final DeviceInfo info;
    private Temperature minTemperature = TemperatureFactory.fromKelvin(0);
    private Optional<Temperature> maxTemperature = Optional.empty();

    /**
     * @param minIntensity
     * @param maxIntensity
     * @param environment
     * @param info
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
        justification = "Updating a reference is better than reallocating objects on the heap")
    public AbstractTemperatureChanger(final double minIntensity, final double maxIntensity, 
        final Environment environment, final DeviceInfo info)  {
        this.info = info;
        this.minIntensity = minIntensity;
        this.maxIntensity = maxIntensity;
        this.intensity = minIntensity;
        this.environment = environment;
    }

    @Override
    public final DeviceInfo getInfo() {
        return this.info;
    }

    @Override
    public final Double getState() {
        return this.intensity;
    }

    @Override
    public final Double getMinValue() {
        return this.minIntensity;
    }

    @Override
    public final Double getMaxValue() {
        return this.maxIntensity;
    }

    @Override
    public final void setState(final Double value) {
        this.intensity = Math.max(Math.min(maxIntensity, value), minIntensity);
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

}
