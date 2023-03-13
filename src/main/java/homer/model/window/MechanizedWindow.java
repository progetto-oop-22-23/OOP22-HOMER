package homer.model.window;

import java.time.Duration;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;
import homer.model.actuator.Actuator;
import homer.model.actuator.SimpleActuator;

/**
 * Implementation of a mechanically controlled {@link Window}.
 */
public final class MechanizedWindow implements Window {

    private static final String DEVICE_TYPE = "MechanizedWindow";
    private final DeviceInfo info = new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    private final int minActuatorState;
    private final int maxActuatorState;
    private final Actuator actuator;

    /**
     * Creates a {@link MechanizedWindow} whose position is controlled by a
     * {@link SimpleActuator} with positions varying between {@code minValue} and
     * {@code maxValue}.
     * 
     * @param minValue The minimum position of the window's actuator.
     * @param maxValue The maximum position of the window's actuator.
     */
    public MechanizedWindow(final int minValue, final int maxValue) {
        this.minActuatorState = minValue;
        this.maxActuatorState = maxValue;
        this.actuator = new SimpleActuator(minValue, maxValue);
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
        return this.minActuatorState;
    }

    @Override
    public Integer getMaxValue() {
        return this.maxActuatorState;
    }

    @Override
    public void setState(final Integer state) {
        this.actuator.command(state);
    }

    @Override
    public void updateTick(final Duration deltaTime) {
        this.actuator.updateTick(deltaTime);
    }

}
