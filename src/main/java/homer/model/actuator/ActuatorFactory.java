package homer.model.actuator;

import homer.common.bounds.Bounds;

/**
 * Factory for {@link Actuator}.
 */
public final class ActuatorFactory {

    /**
     * Default bounds for an actuator, from 0 to 100.
     */
    public static final Bounds<Integer> DEFAULT_BOUNDS = new Bounds<>(0, 100);

    private ActuatorFactory() {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a {@link SimpleActuator} with {@code DEFAULT_BOUNDS} as bounds.
     * 
     * @return a {@link SimpleActuator} with {@code DEFAULT_BOUNDS} as bounds.
     */
    public static Actuator createSimpleActuator() {
        return new SimpleActuator(DEFAULT_BOUNDS);
    }

}
