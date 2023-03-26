package homer.model.outlets;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

import homer.api.AdjustableDevice;
import homer.api.Device;
import homer.api.DeviceState;
import homer.api.PoweredDevice;
import homer.common.time.DurationConverter;
import homer.core.DiscreteObject;

/**
 * Models electrical outlets of the house.
 * 
 * 
 * @author Alessandro Monticelli
 */

public class Outlet implements AdjustableDevice<Double>, DiscreteObject {

    DeviceState state;
    private Optional<Device<?>> device = Optional.empty();

    /**
     * Constructor for class Outlet.
     * c
     * 
     * @param state    The instant power absorption on the outlet.
     * @param minValue The minimum power absorption of the plugged device.
     * @param maxValue The maximum power absorption of the plugged device.
     */
    public Outlet(final DeviceState state) {
        this.state = (OutletState) state;
    }

    /**
     * Constructor for class Outlet. Instantiates a copy of the passed Outlet.
     * 
     * @param outlet the Outlet to copy.
     */
    public Outlet(final Outlet outlet) {
        Objects.requireNonNull(outlet);
        this.state = outlet.getState();
    }

    /**
     * 
     * @return The device state parameters for eg. on/off, intensity.
     */
    @Override
    public DeviceState getState() {
        return (OutletState) this.state;
    }

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
        OutletState state = new OutletState();
        state.addValue(0.0);
        this.setState(state);
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
        final double defaultRandomIncrement = Math.random() * 10 + 1;
        OutletState energy = new OutletState();
        if (this.getDevice().get() instanceof PoweredDevice) {
            final double consumption = ((PoweredDevice) this.getDevice().get()).getInstantConsumption();
            final double hours = DurationConverter.toHours(deltaTime);
            energy.addValue(consumption * hours);
            this.setState(energy);
        } else {
            energy.addValue(Math.min(defaultMaxPower,
                    Math.pow((((OutletState) this.getState()).getPower().get()), 2) + defaultRandomIncrement));
        }
        this.setState(energy);
    }

    @Override
    public void setState(DeviceState state) {
        if (state instanceof OutletState) {
            OutletState outletState = (OutletState) state;
            if (outletState.getPower().isPresent()) {
                ((OutletState) this.state).addValue(outletState.getPower().get());
            }
        }
    }
}
