package homer.impl.outlets;

import java.util.Objects;

import homer.api.AdjustableDevice;
import homer.api.DeviceInfo;

/**
 * Models electrical outlets of the house.
 * 
 * @param <S> Generic type for plugged device, an Outlet doesn't need a device to be plugged in,
 * since it can be used with devices outside the emulated environment.
 * 
 * @author Alessandro Monticelli
 */

public class Outlet<S> implements AdjustableDevice<Double> {

    private final DeviceInfo info;
    private double state;
    private final double minValue;
    private final double maxValue;
    private S device;

    /**
     * Constructor for class Outlet.
     * 
     * @param info     See {@link homer.api.DeviceInfo}.
     * @param state    The instant power absorption on the outlet.
     * @param minValue The minimum power absorption of the plugged device.
     * @param maxValue The maximum power absorption of the plugged device.
     * @param device   The {@link homer.api.Device} plugged to the outlet (can be
     *                 null).
     */
    public Outlet(final DeviceInfo info, final double state, final double minValue,
            final double maxValue, final S device) {
        this.info = info;
        this.state = state;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.device = device;
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
    public void plug(final S device) {
        this.device = Objects.requireNonNull(device);
    }

    /**
     * Unplug the device.
     */
    public void unplug() {
        Objects.requireNonNull(this.device);
        this.device = null;
        this.setValue(0.0);
    }
}
