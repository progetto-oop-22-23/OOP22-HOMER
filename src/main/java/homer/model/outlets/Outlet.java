package homer.model.outlets;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

import homer.api.AdjustableDevice;
import homer.api.Device;
import homer.api.DeviceInfo;
import homer.api.PoweredDevice;
import homer.common.limit.Limit;
import homer.core.DiscreteObject;

/**
 * Models electrical outlets of the house.
 * 
 * 
 * @author Alessandro Monticelli
 */

public class Outlet implements AdjustableDevice<Double>, DiscreteObject {

    private final DeviceInfo info;
    private double state;
    private final double minValue;
    private final double maxValue;
    private Optional<Device<?>> device = Optional.empty();

    /**
     * Constructor for class Outlet.
     * 
     * @param info     See {@link homer.api.DeviceInfo}.
     * @param state    The instant power absorption on the outlet.
     * @param minValue The minimum power absorption of the plugged device.
     * @param maxValue The maximum power absorption of the plugged device.
     */
    public Outlet(final DeviceInfo info, final double state, final double minValue,
            final double maxValue) {
        this.info = Objects.requireNonNull(info);
        this.state = state;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    /**
     * Constructor for class Outlet. Instantiates a copy of the passed Outlet.
     * 
     * @param outlet the Outlet to copy.
     */
    public Outlet(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        this.info = outlet.getInfo();
        this.state = outlet.getState();
        this.minValue = outlet.getMinValue();
        this.maxValue = outlet.getMaxValue();
    }

    /**
     * 
     * @return The device information: ID, Type.
     */
    @Override
    public DeviceInfo getInfo() {
        return this.info;
    }

    /**
     * 
     * @return The device state parameters for eg. on/off, intensity.
     */
    @Override
    public Double getState() {
        return this.state;
    }

    /**
     * 
     * @return The minimum power absorption.
     */
    @Override
    public Double getMinValue() {
        return this.minValue;
    }

    /**
     * 
     * @return The maximum power absorption.
     */
    @Override
    public Double getMaxValue() {
        return this.maxValue;
    }

    /**
     * Sets the instant power absorption.
     * 
     * If a parameter is passed, set {@code this.state}
     * to {@code state}.
     *
     * @param state The new value of {@code state}.
     */
    @Override
    public void setState(final Double state) {
        Objects.requireNonNull(state);
        this.state = Limit.clamp(state, this.getMinValue(), this.getMaxValue());
    }

    /*
     * Sets the instant power absorption.
     * 
     * If a {@link homer.api.PoweredDevice} is plugged, {@code this.state} is set to
     * {@code PoweredDevice.getInstantConsumption()}.
     *
     * public void setState() {
     * this.getDevice().ifPresentOrElse(
     * device -> this.state = ((PoweredDevice) device).getInstantConsumption(),
     * () -> {
     * });
     * }
     */

    /**
     * Plugs a device to the outlet.
     * 
     * @param device The {@link homer.api.Device} to plug.
     */
    public void plug(final Device<?> device) {
        this.unplug();
        this.device = Optional.ofNullable(device);
    }

    /**
     * Unplug the device.
     */
    public void unplug() {
        Objects.requireNonNull(this.device);
        this.device = Optional.empty();
        this.setState(0.0);
    }

    /**
     * @return The plugged device.
     * 
     */
    public Optional<Device<?>> getDevice() {
        return this.device;
    }

    @Override
    public final void updateTick(final Duration deltaTime) {
        final double defaultMaxPower = 150.0;
        final double toHours = 3600.0;
        final double defaultRandomIncrement = Math.random() * 10 + 1;
        double energy;
        if (this.getDevice().get() instanceof PoweredDevice) {
            final double consumption = ((PoweredDevice) this.getDevice().get()).getInstantConsumption();
            final double deltaSeconds = deltaTime.toNanos() / 1e9;
            energy = consumption * deltaSeconds / toHours;
            this.setState(energy);

        } else {
            energy = Math.min(defaultMaxPower, Math.pow(this.getState(), 2) + defaultRandomIncrement);
        }
        this.setState(energy);
    }
}
