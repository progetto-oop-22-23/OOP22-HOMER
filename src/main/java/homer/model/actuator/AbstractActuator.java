package homer.model.actuator;

import java.util.Objects;

import homer.common.bounds.Bounds;

/**
 * This class provides a template implementation for an {@link Actuator},
 * modelling the minimum and maximum positions of the actuator, and exposing
 * getters for retrieving them.
 */
public abstract class AbstractActuator implements Actuator {

    private final Bounds<Integer> positionBounds;

    /**
     * Constructor for {@link AbstractActuator}.
     * 
     * @param positionBounds The minimum and maximum positions of the actuator.
     */
    protected AbstractActuator(final Bounds<Integer> positionBounds) {
        this.positionBounds = Objects.requireNonNull(positionBounds);
    }

    @Override
    public final Bounds<Integer> getBounds() {
        return this.positionBounds;
    }

}
