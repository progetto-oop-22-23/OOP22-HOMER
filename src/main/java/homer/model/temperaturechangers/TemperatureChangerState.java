package homer.model.temperaturechangers;

import java.util.Optional;

import homer.api.DeviceState;

/**
 * Models the state of a TemperatureChanger object.
 */
public final class TemperatureChangerState implements DeviceState {
    private Optional<Double> minIntensity = Optional.empty();
    private Optional<Double> maxIntensity = Optional.empty();
    private Optional<Double> currentIntensity = Optional.empty();
    private Optional<String> type = Optional.empty();
    /**
     * Normal Heating type.
     */
    public static final String HEATING = "HEATING";
    /**
     * Normal Air conditioning type.
     */
    public static final String AIRCONDITIONING = "AIR CONDITIONING";

    /**
     * 
     * Returns the minimum intensity.
     * 
     * @return the minimum intensity.
     */
    public Optional<Double> getMinIntensity() {
        return this.minIntensity;
    }

    /**
     * 
     * Returns the maximum intensity.
     * 
     * @return the maximum intensity.
     */
    public Optional<Double> getMaxIntensity() {
        return this.maxIntensity;
    }

    /**
     * 
     * Returns the current intensity.
     * 
     * @return the current intensity.
     */
    public Optional<Double> getCurrentIntensity() {
        return this.currentIntensity;
    }

    /**
     * 
     * @param min the minimum intensity
     * @return the updated instance.
     */
    public TemperatureChangerState addMinIntensity(final double min) {
        this.minIntensity = Optional.of(min);
        return this;
    }

    /**
     * 
     * @param max the max intensity
     * @return the updated instance.
     */
    public TemperatureChangerState addMaxIntensity(final double max) {
        this.maxIntensity = Optional.of(max);
        return this;
    }

    /**
     * 
     * @param current the current intensity.
     * @return the updated instance.
     */
    public TemperatureChangerState addCurrentIntensity(final double current) {
        this.currentIntensity = Optional.of(current);
        return this;
    }

    /**
     * 
     * @param type the {@link TemperatureChanger}'s type
     * @return the updated instance.
     */
    public TemperatureChangerState addTemperatureChangerType(final String type) {
        this.type = Optional.of(type);
        return this;
    }

    /**
     * 
     * Returns the type of the temperature changer.
     * @return the type of the temperature changer.
     */
    public Optional<String> getType() {
        return this.type;
    }
}
