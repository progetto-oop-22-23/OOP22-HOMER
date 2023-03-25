package homer.model.temperaturechangers;

import java.util.Optional;

import homer.api.DeviceState;

public class TemperatureChangerState implements DeviceState {
    Optional<Double> min = Optional.empty();
    Optional<Double> max = Optional.empty();
    Optional<Double> intensity = Optional.empty();

    public Optional<Double> getMin() {
        return this.min;
    }
    
    public Optional<Double> getMax() {
        return this.max;
    }

    public Optional<Double> getIntensity() {
        return this.intensity;
    }

    TemperatureChangerState addMin(double min) {
        this.min = Optional.of(min);
        return this;
    }

    TemperatureChangerState addMax(double max) {
        this.max = Optional.of(max);
        return this;
    }

    TemperatureChangerState addValue(double value) {
        this.intensity = Optional.of(value);
        return this;
    }
}
