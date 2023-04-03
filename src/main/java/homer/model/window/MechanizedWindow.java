package homer.model.window;

import java.util.Optional;

import homer.api.state.ActuatedDeviceState;
import homer.model.actuator.AbstractActuatedDevice;
import homer.model.actuator.Actuator;

/**
 * Implementation of a {@link Window} mechanically controlled by an
 * {@link Actuator} in a discrete-time simulation.
 */
public final class MechanizedWindow extends AbstractActuatedDevice implements Window {

    /**
     * Creates a {@link MechanizedWindow} whose position is controlled by an
     * {@link Actuator}.
     * 
     * @param actuator The {@link Actuator} controlling the window.
     */
    public MechanizedWindow(final Actuator actuator) {
        super(actuator, Optional.of(ActuatedDeviceState.WINDOW));
    }

}
