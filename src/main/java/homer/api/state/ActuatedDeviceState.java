package homer.api.state;

import java.util.Optional;

import homer.api.DeviceState;
import homer.common.bounds.Bounds;

/**
 * Implementation of {@link DeviceState} for an {@link ActuatedDevice}.
 */
public final class ActuatedDeviceState implements DeviceState {

    private final Optional<Bounds<Integer>> positionBounds;
    private final int position;
    /**
     * Mechanized blinds.
     */
    public static final String BLINDS = "Mechanized blinds";
    /**
     * Mechanzized door.
     */
    public static final String DOOR = "Mechanized Door";
    /**
     * Mechanized window.
     */
    public static final String WINDOW = "Mechanized Window";
    private final Optional<String> type;

    /**
     * Creates a new {@link ActuatedDeviceState} given the position and the bounds.
     * 
     * @param position       the actuator position.
     * @param positionBounds the actuator bounds.
     * @param type
     */
    public ActuatedDeviceState(final int position, final Bounds<Integer> positionBounds, final Optional<String> type) {
        this.position = position;
        this.positionBounds = Optional.of(positionBounds);
        this.type = type;
    }

    /**
     * Creates a new {@link ActuatedDeviceState} given the actuator position.
     * Used to set a new state.
     * 
     * @param position the new commanded position.
     * @param type the device's type.
     */
    public ActuatedDeviceState(final int position, final Optional<String> type) {
        this.position = position;
        this.positionBounds = Optional.empty();
        this.type = type;
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

    /**
     * 
     * Returnss the device's type.
     * 
     * @return the device's type.
     */
    public Optional<String> getType() {
        return this.type;
    }

}
