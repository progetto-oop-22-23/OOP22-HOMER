package homer.model.actuator;

import java.time.Duration;

import homer.common.bounds.Bounds;
import homer.common.limit.Limit;

/**
 * Implementation of an {@link Actuator} with instant response to the commanded
 * position.
 */
public final class SimpleActuator extends AbstractActuator {

    private int commandedPosition;
    private int currentPosition;

    /**
     * Creates a new {@link SimpleActuator} with the current position set to the
     * minimum position.
     * 
     * @param positionBounds The minimum and maximum positions of the actuator.
     */
    public SimpleActuator(final Bounds<Integer> positionBounds) {
        super(positionBounds);
        this.currentPosition = getMinPosition();
    }

    @Override
    public void command(final int commandedPosition) {
        this.commandedPosition = commandedPosition;
    }

    @Override
    public int getPosition() {
        return this.currentPosition;
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        this.currentPosition = Limit.limit(this.commandedPosition, getMinPosition(), getMaxPosition());
    }

}
