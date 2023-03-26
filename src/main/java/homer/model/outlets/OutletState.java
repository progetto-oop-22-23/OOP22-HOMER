package homer.model.outlets;

import java.util.Optional;

import homer.api.DeviceState;

public final class OutletState implements DeviceState {
    private Optional<Double> minPower = Optional.empty();
    private Optional<Double> maxPower = Optional.empty();
    private Optional<Double> power = Optional.empty();

    /**
     * Returns the minimum power.
     * 
     * @return minimum power.
     */
    public Optional<Double> getMin() {
        return this.minPower;
    }

    /**
     * Returns the maximum power.
     * 
     * @return maximum power.
     */
    public Optional<Double> getMax() {
        return this.maxPower;
    }

    /**
     * Return the instant power.
     * 
     * @return instant power.
     */
    public Optional<Double> getPower() {
        return this.power;
    }

    /**
     * Sets the minimum power.
     * 
     * @param minPower the new minimum power value.
     * @return the {@link homer.model.outlets.Outlet} state.
     */
    OutletState addMin(final double minPower) {
        this.minPower = Optional.of(minPower);
        return this;
    }

    /**
     * Sets the maximum power.
     * 
     * @param minPower the new maximum power value.
     * @return the {@link homer.model.outlets.Outlet} state.
     */
    OutletState addMax(final double maxPower) {
        this.maxPower = Optional.of(maxPower);
        return this;
    }

    /**
     * Sets the instant power.
     * 
     * @param minPower the new instant power value.
     * @return the {@link homer.model.outlets.Outlet} state.
     */
    public OutletState addValue(final double value) {
        this.power = Optional.of(value);
        return this;
    }
}
