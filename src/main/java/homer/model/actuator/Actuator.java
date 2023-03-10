package homer.model.actuator;

import homer.core.DiscreteObject;

/**
 * This interface models an actuator.
 * 
 * @param <S> The type used for the actuator position.
 */
public interface Actuator<S extends Number> extends DiscreteObject {

    /**
     * Commands the actuator to a new position.
     * 
     * @param commandedPosition The commanded new position.
     */
    void command(S commandedPosition);

    /**
     * Returns the current actuator position.
     * 
     * @return The current actuator position.
     */
    S getPosition();

}
