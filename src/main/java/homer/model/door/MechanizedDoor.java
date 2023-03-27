package homer.model.door;

import homer.model.actuator.AbstractActuatedDevice;
import homer.model.actuator.Actuator;

/**
 * Implementation of a {@link Door} mechanically controlled by an
 * {@link Actuator} in a discrete-time simulation.
 */
public class MechanizedDoor extends AbstractActuatedDevice implements Door {

    /**
     * Creates a {@link MechanizedDoor} whose position is controlled by an
     * {@link Actuator}.
     * 
     * @param actuator The {@link Actuator} controlling the window.
     */
    public MechanizedDoor(final Actuator actuator) {
        super(actuator);
    }

}
