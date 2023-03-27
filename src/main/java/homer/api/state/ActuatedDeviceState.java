package homer.api.state;

import java.util.Optional;

import homer.api.DeviceState;
import homer.common.bounds.Bounds;

/**
 * Implementation of {@link DeviceState} for an {@link ActuatedDevice}.
 */
public class ActuatedDeviceState implements DeviceState {

    private final Optional<Bounds<Integer>> positionBounds;
    private final int position;

    /**
     * Creates a new {@link ActuatedDeviceState} given the position and the bounds.
     * 
     * @param position       the actuator position.
     * @param positionBounds the actuator bounds.
     */
    public ActuatedDeviceState(int position, Bounds<Integer> positionBounds) {
        this.position = position;
        this.positionBounds = Optional.of(positionBounds);
    }

    /**
     * Creates a new {@link ActuatedDeviceState} given the actuator position.
     * Used to set a new state.
     * 
     * @param position the new commanded position.
     */
    public ActuatedDeviceState(int position) {
        this.position = position;
        this.positionBounds = Optional.empty();
    }

    /**
     * Returns the actuator position.
     * 
     * @return the actuator position.
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Returns the actuator position bounds.
     * 
     * @return the actuator position bounds.
     */
    public Optional<Bounds<Integer>> getPositionBounds() {
        return this.positionBounds;
    }

}
