package homer.model.actuator;

import java.time.Duration;
import java.util.Objects;

/**
 * Implementation of an {@link Actuator} with instant response to the commanded
 * position.
 * 
 * @param <S> The type used for the actuator position.
 */
public final class SimpleActuator<S extends Number> extends AbstractActuator<S> {

    private S commandedPosition;
    private S currentPosition;

    /**
     * Creates a new {@link SimpleActuator} with the current position set to the
     * minimum position.
     * 
     * @param minPosition The minimum position of the actuator.
     * @param maxPosition The maximum position of the actuator.
     */
    public SimpleActuator(final S minPosition, final S maxPosition) {
        super(minPosition, maxPosition);
        this.currentPosition = getMinPosition();
    }

    @Override
    public void command(final S commandedPosition) {
        this.commandedPosition = Objects.requireNonNull(commandedPosition);
    }

    @Override
    public S getPosition() {
        return this.currentPosition;
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        this.currentPosition = commandedPosition;
    }

}
