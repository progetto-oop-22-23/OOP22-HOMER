package homer.model.actuator;

import java.util.Objects;

/**
 * This class provides a template implementation for an {@link Actuator},
 * modelling the minimum and maximum positions of the actuator, and exposing
 * getters for retrieving them.
 */
public abstract class AbstractActuator implements Actuator {

    private final int minPosition;
    private final int maxPosition;

    /**
     * Constructor for {@link AbstractActuator}.
     * 
     * @param minPosition The minimum position of the actuator.
     * @param maxPosition The maximum position of the actuator.
     */
    protected AbstractActuator(final int minPosition, final int maxPosition) {
        this.minPosition = Objects.requireNonNull(minPosition);
        this.maxPosition = Objects.requireNonNull(maxPosition);
    }

    /**
     * Returns the minimum position of the actuator.
     * 
     * @return The minimum position of the actuator.
     */
    protected final int getMinPosition() {
        return this.minPosition;
    }

    /**
     * Returns the maximum position of the actuator.
     * 
     * @return The maximum position of the actuator.
     */
    protected final int getMaxPosition() {
        return this.maxPosition;
    }

}
