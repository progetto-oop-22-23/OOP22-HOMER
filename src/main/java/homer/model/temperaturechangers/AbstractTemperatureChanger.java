package homer.model.temperaturechangers;

import homer.api.DeviceState;
import homer.api.PoweredDevice;
import homer.api.PoweredDeviceInfo;
import homer.api.PoweredDeviceInfoImpl;
import homer.common.limit.Limit;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import homer.common.time.DurationConverter;
import homer.model.environment.Environment;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;

import java.time.Duration;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Uses template method on updateTick. At a default intensity, it changes the
 * temperature by
 * 1 Celsius degree each hour. By default, minimum temperature is the absolute
 * 0, and the maximum temperature
 * is not set.
 */
public abstract class AbstractTemperatureChanger implements TemperatureChanger, PoweredDevice {

    private final double maxIntensity;
    private final double minIntensity;
    private double intensity;
    private final Environment environment;
    private Temperature minTemperature = TemperatureFactory.fromKelvin(0);
    private Optional<Temperature> maxTemperature = Optional.empty();
    private final PoweredDeviceInfo power;
    private static final double MAX_CONSUMPTION = 1000;

    @Override
    public final double getInstantConsumption() {
        return this.getPowerInfo().getOutlet().getState().getPower().get();
    }

    @Override
    public final void setInstantConsumption(final double instantConsumption) {
        this.power.getOutlet().getState().addValue(instantConsumption);
    }

    /**
     * @param minIntensity the minimum intensity allowed.
     * @param maxIntensity the maximum intensity allowed.
     * @param environment  the environment that is modified by the heating device.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", 
        justification = "Updating a reference is better than reallocating objects on the heap")
    public AbstractTemperatureChanger(final double minIntensity, final double maxIntensity,
            final Environment environment) {
        this.minIntensity = minIntensity;
        this.maxIntensity = maxIntensity;
        this.intensity = minIntensity;
        this.environment = environment;
        this.power = new PoweredDeviceInfoImpl(MAX_CONSUMPTION, OutletFactory.lOutlet(0));
    }

    /**
     * 
     * Returns The temperaturechanger's state that we want to pass to the view.
     * 
     * @return The temperaturechanger's state that we want to pass to the view.
     */
    protected final TemperatureChangerState getTemperatureState() {
        return new TemperatureChangerState().addCurrentIntensity(intensity).addMaxIntensity(maxIntensity)
                .addMinIntensity(minIntensity);
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
    public final void setState(final DeviceState state) {
        if (state instanceof TemperatureChangerState) {
            final TemperatureChangerState temperatureChangerState = (TemperatureChangerState) state;
            if (temperatureChangerState.getCurrentIntensity().isPresent()) {
                this.intensity = temperatureChangerState.getCurrentIntensity().get();
            }
        }
    }

    @Override
    public final void plug(final Outlet outlet) {
        this.power.setOutlet(outlet);
    }

    @Override
    public final PoweredDeviceInfo getPowerInfo() {
        return new PoweredDeviceInfoImpl(this.power.getMaxConsumption(), this.power.getOutlet());
    }

    /**
     * 
     * @param deltaTime the delta time elapsed from last tick.
     */
    private void updateConsumption(final Duration deltaTime) {
        final double maxConsumption = this.getPowerInfo().getMaxConsumption();
        final double hours = DurationConverter.toHours(deltaTime);
        final double newConsumption = maxConsumption * Math.pow(this.intensity, 2) * hours;
        this.setInstantConsumption(
                Limit.clamp(newConsumption, this.getPowerInfo().getMinConsumption(), maxConsumption));
    }

    @Override
    public final void updateTick(final Duration deltaTime) {
        this.updateConsumption(deltaTime);
        this.updateTemperature(deltaTime);
    }

    abstract void updateTemperature(Duration deltaTime);

}
