package homer.model.temperaturechangers;

import java.time.Duration;

import homer.api.DeviceInfo;
import homer.common.Temperature;
import homer.model.environment.Environment;

/**
 * Uses template method on updateTick.
 */
public abstract class AbstractTemperatureChanger implements TemperatureChanger {

    private final double step = 1/(1000f * 3600); // used to normalize the intensity
    private final double maxIntensity;
    private final double minIntensity;
    private double intensity;
    private final Environment environment;
    private DeviceInfo info;
    private Temperature minTemperature;
    private Temperature maxTemperature;

    /**
     * 
     * @param minIntensity
     * @param maxIntensity
     * @param currentIntensity
     * @param environment
     * @param minTemperature
     * @param maxTemperature
     */
    public AbstractTemperatureChanger(final double minIntensity, final double maxIntensity, 
        final double currentIntensity, final Environment environment, final Temperature minTemperature, 
        final Temperature maxTemperature) {
        this.minIntensity = minIntensity;
        this.maxIntensity = maxIntensity;
        this.intensity = currentIntensity;
        this.environment = environment;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
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
    public Temperature getMaxTemperature() {
        return this.maxTemperature;
    }

    /**
     * 
     * @return minimum temperature allowed
     */
    public Temperature getMinTemperature() {
        return this.minTemperature;
    }

    /**
     * 
     * @return the environment associated with the object 
     */
    public final Environment getEnvironment() {
        return this.environment;
    }

    /**
     * 
     * @return normalization value
     */
    public double getStep() {
        return this.step;
    }

    @Override
    public abstract void updateTick(Duration deltaTime);
}
