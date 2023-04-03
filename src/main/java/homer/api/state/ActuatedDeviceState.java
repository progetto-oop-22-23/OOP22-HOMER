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
    private Optional<String> type = Optional.empty();

    /**
     * Creates a new {@link ActuatedDeviceState} given the position and the bounds.
     * 
     * @param position       the actuator position.
     * @param positionBounds the actuator bounds.
     */
    public ActuatedDeviceState(final int position, final Bounds<Integer> positionBounds) {
        this.position = position;
        this.positionBounds = Optional.of(positionBounds);
    }

    /**
     * Creates a new {@link ActuatedDeviceState} given the actuator position.
     * Used to set a new state.
     * 
     * @param position the new commanded position.
     */
    public ActuatedDeviceState(final int position) {
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

    /**
     * 
     * @param string the new type to be set.
     */
    public void setType(final String string) {
        this.type = Optional.of(string);
    }

    /**
     * 
     * Returnss the device's type.
     * @return the device's type.
     */
    public Optional<String> getType() {
        return this.type;
    }

}
