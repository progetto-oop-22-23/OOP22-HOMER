package homer.model.temperaturechangers;

import java.time.Duration;

import homer.api.DeviceInfo;
import homer.common.Temperature;
import homer.model.environment.Environment;

public abstract class AbstractTemperatureChanger implements TemperatureChanger {

    protected final double STEP = 1; // used to normalize the intensity
    protected final double maxIntensity;
    protected final double minIntensity;
    protected double intensity;
    protected Environment environment;
    private DeviceInfo info;
    protected Temperature minTemperature;
    protected Temperature maxTemperature;

    public AbstractTemperatureChanger(final double minIntensity, final double maxIntensity, 
        final double currentIntensity, Environment environment, Temperature minTemperature, Temperature maxTemperature) {
        this.minIntensity = minIntensity;
        this.maxIntensity = maxIntensity;
        this.intensity = currentIntensity;
        this.environment = environment;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    @Override
    public DeviceInfo getInfo() {
        return this.info;
    }

    @Override
    public Double getState() {
        return this.intensity;
    }

    @Override
    public Double getMinValue() {
        return this.minIntensity;
    }

    @Override
    public Double getMaxValue() {
        return this.maxIntensity;
    }

    @Override
    public void setState(Double value) {
        this.intensity = Math.max(Math.min(maxIntensity, value), minIntensity);
    }

    public abstract void updateTick(Duration deltaTime);
}