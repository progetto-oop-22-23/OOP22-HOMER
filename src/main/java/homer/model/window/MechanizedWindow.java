package homer.model.window;

import java.time.Duration;
import java.util.Objects;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.common.bounds.Bounds;
import homer.model.actuator.Actuator;
import homer.model.actuator.SimpleActuator;

/**
 * Implementation of a mechanically controlled {@link Window}.
 */
public final class MechanizedWindow implements Window {
    /**
     * The device type label.
     */
    public static final String DEVICE_TYPE = "MechanizedWindow";
    private final DeviceInfo info = new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    private final Bounds<Integer> actuatorBounds;
    private final Actuator actuator;

    /**
     * Creates a {@link MechanizedWindow} whose position is controlled by a
     * {@link SimpleActuator} with positions varying between {@code minValue} and
     * {@code maxValue}.
     * 
     * @param actuatorBounds The minimum and maximum position of the window's
     *                       actuator.
     * @throws IllegalArgumentException if {@code minValue} is greater than
     *                                  {@code maxValue}
     */
    public MechanizedWindow(final Bounds<Integer> actuatorBounds) {
        this.actuatorBounds = Objects.requireNonNull(actuatorBounds);
        this.actuator = new SimpleActuator(actuatorBounds);
    }

    @Override
    public DeviceInfo getInfo() {
        return this.info;
    }

    @Override
    public Integer getState() {
        return this.actuator.getPosition();
    }

    @Override
    public Integer getMinValue() {
        return this.actuatorBounds.getLowerBound();
    }

    @Override
    public Integer getMaxValue() {
        return this.actuatorBounds.getUpperBound();
    }

    @Override
    public void setState(final Integer state) {
        this.actuator.command(Objects.requireNonNull(state));
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        this.actuator.updateTick(deltaTime);
    }

}
