package homer.model.outlets;

import java.time.Duration;
import java.util.Objects;

import homer.api.AdjustableDevice;
import homer.api.DeviceState;
import homer.core.DiscreteObject;

/**
 * Models electrical outlets of the house.
 * 
 * 
 * @author Alessandro Monticelli
 */

public final class Outlet implements AdjustableDevice<OutletState>, DiscreteObject {

    private final OutletState state;

    /**
     * Constructor for class Outlet.
     * c
     * 
     * @param state See {@link homer.model.outlets.OutletState}
     */
    public Outlet(final DeviceState state) {
        if (state instanceof OutletState) {
            this.state = (OutletState) state;
        } else {
            this.state = new OutletState();
        }
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
            final OutletState outletState = (OutletState) state;
            if (outletState.getPower().isPresent()) {
                this.state.addValue(outletState.getPower().get());
            }
        }
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        final double randomIncrement = Math.sin(deltaTime.toSeconds());
        final OutletState energy = this.getState();
        if (energy.getPower().isPresent()) {
            final double newPower = energy.getPower().get() + randomIncrement;
            if (newPower >= 0) {
                energy.addValue(newPower);
            }
            this.setState(energy);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Outlet outlet = (Outlet) o;

        return Objects.equals(state, outlet.getState());
    }

    @Override
    public int hashCode() {
        return this.getState().hashCode();
    }
}
