package homer.model.actuator;

import java.util.Objects;

/**
 * This class provides a template implementation for an {@link Actuator},
 * modelling the minimum and maximum positions of the actuator, and exposing
 * getters for retrieving them.
 * 
 * @param <S> The type used for the actuator position.
 */
public abstract class AbstractActuator<S extends Number> implements Actuator<S> {

    private final S minPosition;
    private final S maxPosition;

    /**
     * Constructor for {@link AbstractActuator}.
     * 
     * @param minPosition The minimum position of the actuator.
     * @param maxPosition The maximum position of the actuator.
     */
    protected AbstractActuator(final S minPosition, final S maxPosition) {
        this.minPosition = Objects.requireNonNull(minPosition);
        this.maxPosition = Objects.requireNonNull(maxPosition);
    }

    /**
     * Returns the minimum position of the actuator.
     * 
     * @return The minimum position of the actuator.
     */
    protected final S getMinPosition() {
        return this.minPosition;
    }

    /**
     * Returns the maximum position of the actuator.
     * 
     * @return The maximum position of the actuator.
     */
    protected final S getMaxPosition() {
        return this.maxPosition;
    }

}
