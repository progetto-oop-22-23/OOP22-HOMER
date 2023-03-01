package homer.temperaturechangers;

import java.util.function.Function;

import homer.api.AdjustableDevice;
import homer.api.DeviceInfo;

public abstract class TemperatureChangerDevice implements AdjustableDevice<Double> {

    protected final double maxValue;
    protected final double minValue;
    protected double currentValue;
    protected long lastCall = System.currentTimeMillis();
    protected Function<Long, Double> function;
    protected double STEP;

    public TemperatureChangerDevice(final double minValue, final double maxValue, final double currentValue, final Function<Long, Double> function) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
        this.function = function;
    }

    @Override
    public DeviceInfo getInfo() {
        return null;
    }

    @Override
    abstract public Double getState();

    @Override
    public Double getMinValue() {
        return this.minValue;
    }

    @Override
    public Double getMaxValue() {
        return this.maxValue;
    }

    @Override
    public Double getValue() {
        return this.getState();
    }

    @Override
    public void setValue(Double value) {
        this.currentValue = value;
    }
}
