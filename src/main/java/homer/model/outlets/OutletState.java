package homer.model.outlets;

import java.util.Optional;

import homer.api.DeviceState;

public class OutletState implements DeviceState {
    Optional<Double> minPower = Optional.empty();
    Optional<Double> maxPower = Optional.empty();
    Optional<Double> power = Optional.empty();

    public Optional<Double> getMin() {
        return this.minPower;
    }

    public Optional<Double> getMax() {
        return this.maxPower;
    }

    public Optional<Double> getPower() {
        return this.power;
    }

    OutletState addMin(double minPower) {
        this.minPower = Optional.of(minPower);
        return this;
    }

    OutletState addMax(double maxPower) {
        this.maxPower = Optional.of(maxPower);
        return this;
    }

    OutletState addValue(double value) {
        this.power = Optional.of(value);
        return this;
    }
}
