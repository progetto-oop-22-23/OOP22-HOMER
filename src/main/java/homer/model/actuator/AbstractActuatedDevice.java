package homer.model.actuator;

import java.time.Duration;
import java.util.Objects;

/**
 * Abstract implementation of an {@link ActuatedDevice}.
 */
public class AbstractActuatedDevice implements ActuatedDevice {

    private final Actuator actuator;

    /**
     * Creates a {@link AbstractActuatedDevice} whose position is controlled by an
     * {@link Actuator}.
     * 
     * @param actuator The {@link Actuator} controlling the device.
     */
    public AbstractActuatedDevice(final Actuator actuator) {
        this.actuator = Objects.requireNonNull(actuator);
    }

    @Override
    public final Integer getState() {
        return this.actuator.getPosition();
    }

    @Override
    public final Integer getMinValue() {
        return this.actuator.getBounds().getLowerBound();
    }

    @Override
    public final Integer getMaxValue() {
        return this.actuator.getBounds().getUpperBound();
    }

    @Override
    public final void setState(final Integer state) {
        this.actuator.command(Objects.requireNonNull(state));
    }

    @Override
    public final void updateTick(final Duration deltaTime) {
        this.actuator.updateTick(deltaTime);
    }

}
