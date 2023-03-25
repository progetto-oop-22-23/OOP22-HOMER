package homer.model.window;

import homer.model.actuator.AbstractActuatedDevice;
import homer.model.actuator.Actuator;

/**
 * Implementation of a mechanically controlled {@link Window}.
 */
public final class MechanizedWindow extends AbstractActuatedDevice implements Window {

    /**
     * Creates a {@link MechanizedWindow} whose position is controlled by an
     * {@link Actuator}.
     * 
     * @param actuator The {@link Actuator} controlling the window.
     */
    public MechanizedWindow(final Actuator actuator) {
        super(actuator);
    }

}
