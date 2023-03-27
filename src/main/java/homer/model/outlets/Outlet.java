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

public final class Outlet implements AdjustableDevice<OutletState>, DiscreteObject {

    OutletState state;
    private Optional<Device<?>> device = Optional.empty();

    /**
     * Constructor for class Outlet.
     * c
     * 
     * @param state    See {@link homer.model.outlets.OutletState}
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
    public OutletState getState() {
        return this.state;
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
        OutletState state = this.getState();
        state.addValue(0.0);
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
        OutletState energy = this.getState();
        if (this.getDevice().get() instanceof PoweredDevice) {
            final double consumption = ((PoweredDevice) this.getDevice().get()).getInstantConsumption();
            final double hours = DurationConverter.toHours(deltaTime);
            energy.addValue(consumption * hours);
            this.setState(energy);
        } else {
            energy.addValue(Math.min(defaultMaxPower,
                    Math.pow(this.getState().getPower().get(), 2) + defaultRandomIncrement));
        }
        this.setState(energy);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof OutletState) {
            OutletState outletState = (OutletState) state;
            if (outletState.getPower().isPresent()) {
                this.state.addValue(outletState.getPower().get());
            }
        }
    }
}
