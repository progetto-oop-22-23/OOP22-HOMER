package homer.model.outlets;

import java.util.Objects;
import java.util.Optional;

import homer.api.AdjustableDevice;
import homer.api.Device;
import homer.api.DeviceInfo;

/**
 * Models electrical outlets of the house.
 * 
 * 
 * @author Alessandro Monticelli
 */

public class Outlet implements AdjustableDevice<Double> {

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
     * 
     * @return The istant power absorption.
     */
    @Override
    public Double getValue() {
        return this.state;
    }

    /**
     * Sets the istant power absorption.
     * 
     * 
     * 
     * @param value The new value of {@code state}.
     */
    @Override
    public void setValue(final Double value) {
        this.state = value;
    }

    /**
     * Plugs a device to the outlet.
     * 
     * @param device The {@link homer.api.Device} to plug.
     */
    public void plug(final Device<?> device) {
        this.device = Optional.of(Objects.requireNonNull(device));
    }

    /**
     * Unplug the device.
     */
    public void unplug() {
        Objects.requireNonNull(this.device);
        this.device = Optional.empty();
        this.setValue(0.0);
    }

    /**
     * @return The plugged device.
     * 
     */
    public Optional<Device<?>> getDevice() {
        return this.device;
    }
}
