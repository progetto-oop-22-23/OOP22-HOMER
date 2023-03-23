package homer.model.window;

import java.time.Duration;
import java.util.Objects;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.model.actuator.Actuator;

/**
 * Implementation of a mechanically controlled {@link Window}.
 */
public final class MechanizedWindow implements Window {
    /**
     * The device type label.
     */
    public static final String DEVICE_TYPE = "MechanizedWindow";
    private final DeviceInfo info = new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    private final Actuator actuator;

    /**
     * Creates a {@link MechanizedWindow} whose position is controlled by an
     * {@link Actuator}.
     * 
     * @param actuator The {@link Actuator} controlling the window.
     */
    public MechanizedWindow(final Actuator actuator) {
        this.actuator = Objects.requireNonNull(actuator);
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
        return this.actuator.getBounds().getLowerBound();
    }

    @Override
    public Integer getMaxValue() {
        return this.actuator.getBounds().getUpperBound();
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
