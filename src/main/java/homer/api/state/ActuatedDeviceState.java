package homer.api.state;

import java.util.Optional;

import homer.api.DeviceState;
import homer.common.bounds.Bounds;

public class ActuatedDeviceState implements DeviceState {

    private final Optional<Bounds<Integer>> positionBounds;
    private final int position;

    public ActuatedDeviceState(int position, Bounds<Integer> positionBounds) {
        this.position = position;
        this.positionBounds = Optional.of(positionBounds);
    }

    public ActuatedDeviceState(int position) {
        this.position = position;
        this.positionBounds = Optional.empty();
    }

    public int getPosition() {
        return this.position;
    }

    public Optional<Bounds<Integer>> getPositionBounds() {
        return this.positionBounds;
    }

}
