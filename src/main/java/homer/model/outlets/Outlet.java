package homer.model.outlets;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

import homer.api.AdjustableDevice;
import homer.api.DeviceState;
import homer.common.time.DurationConverter;
import homer.core.DiscreteObject;

/**
 * Models electrical outlets of the house.
 * 
 * 
 * @author Alessandro Monticelli
 */

public final class Outlet implements AdjustableDevice<OutletState>, DiscreteObject {

    private OutletState state;

    /**
     * Constructor for class Outlet.
     * c
     * 
     * @param state See {@link homer.model.outlets.OutletState}
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

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof OutletState) {
            OutletState outletState = (OutletState) state;
            if (outletState.getPower().isPresent()) {
                this.state.addValue(outletState.getPower().get());
            }
        }
    }

    @Override
    public final void updateTick(final Duration deltaTime) {
        final double defaultMaxPower = 150.0;
        final double defaultRandomIncrement = Math.random() * 10 + 1;
        OutletState energy = this.getState();
        if (this.getState().getPower().equals(Optional.empty())) {
            energy.addValue(Math.min(defaultMaxPower,
                    Math.pow(this.getState().getPower().get(), 2) + defaultRandomIncrement));
        } else {
            final double consumption = this.getState().getPower().get();
            final double hours = DurationConverter.toHours(deltaTime);
            energy.addValue(consumption * hours);
        }
        this.setState(energy);
    }
}
