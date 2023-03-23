package homer.model.actuator;

import homer.common.bounds.Bounds;
import homer.core.DiscreteObject;

/**
 * This interface models an actuator.
 */
public interface Actuator extends DiscreteObject {

    /**
     * Commands the actuator to a new position.
     * 
     * @param commandedPosition The commanded new position.
     */
    void command(int commandedPosition);

    /**
     * Returns the current actuator position.
     * 
     * @return The current actuator position.
     */
    int getPosition();

    /**
     * Returns the actuator position bounds.
     * 
     * @return the actuator position bounds.
     */
    Bounds<Integer> getBounds();

}
