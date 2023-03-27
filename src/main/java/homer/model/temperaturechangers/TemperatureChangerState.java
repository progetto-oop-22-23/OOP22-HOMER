package homer.model.temperaturechangers;

import java.util.Optional;

import homer.api.DeviceState;

/**
 * Models the state of a TemperatureChanger object.
 */
public final class TemperatureChangerState implements DeviceState {
    Optional<Double> min = Optional.empty();
    Optional<Double> max = Optional.empty();
    Optional<Double> current = Optional.empty();

    /**
     * 
     * Returns the minimum intensity.
     * 
     * @return the minimum intensity.
     */
    public Optional<Double> getMinIntensity() {
        return this.min;
    }

    /**
     * 
     * Returns the maximum intensity.
     * 
     * @return the maximum intensity.
     */
    public Optional<Double> getMaxIntensity() {
        return this.max;
    }

    /**
     * 
     * @return
     */
    public Optional<Double> getCurrentIntensity() {
        return this.current;
    }

    /**
     * 
     * @param min the minimum intensity
     * @return the updated instance.
     */
    public TemperatureChangerState addMinIntensity(double min) {
        this.min = Optional.of(min);
        return this;
    }

    /**
     * 
     * @param max the max intensity
     * @return the updated instance.
     */
    public TemperatureChangerState addMaxIntensity(double max) {
        this.max = Optional.of(max);
        return this;
    }

    /**
     * 
     * @param current the current intensity.
     * @return the updated instance.
     */
    public TemperatureChangerState addCurrentIntensity(double current) {
        this.current = Optional.of(current);
        return this;
    }
}
