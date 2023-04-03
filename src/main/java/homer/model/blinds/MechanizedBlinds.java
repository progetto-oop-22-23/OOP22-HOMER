package homer.model.blinds;

import java.util.Optional;

import homer.api.state.ActuatedDeviceState;
import homer.model.actuator.AbstractActuatedDevice;
import homer.model.actuator.Actuator;

/**
 * Implementation of {@link Blinds} mechanically controlled by an
 * {@link Actuator} in a discrete-time simulation.
 */
public final class MechanizedBlinds extends AbstractActuatedDevice implements Blinds {

    /**
     * Creates {@link MechanizedBlinds} whose position is controlled by an
     * {@link Actuator}.
     * 
     * @param actuator The {@link Actuator} controlling the blinds.
     */
    public MechanizedBlinds(final Actuator actuator) {
        super(actuator, Optional.of(ActuatedDeviceState.BLINDS));
    }

}
