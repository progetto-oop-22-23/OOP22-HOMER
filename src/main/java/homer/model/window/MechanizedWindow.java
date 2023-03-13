package homer.model.window;

import homer.DeviceInfoImpl;
import homer.api.AdjustableDevice;
import homer.api.DeviceIdImpl;
import homer.api.DeviceInfo;

/**
 * Implementation of a {@link AdjustableDevice} representing a mechanically
 * controlled window.
 */
public final class MechanizedWindow implements AdjustableDevice<Integer> {

    private static final String DEVICE_TYPE = "MechanizedWindow";
    private final DeviceInfo info = new DeviceInfoImpl(new DeviceIdImpl(), DEVICE_TYPE);
    private final int minActuatorState;
    private final int maxActuatorState;
    private int actuatorState;

    public MechanizedWindow(final int minValue, final int maxValue) {
        this.minActuatorState = minValue;
        this.maxActuatorState = maxValue;
        this.actuatorState = minValue;
    }

    @Override
    public DeviceInfo getInfo() {
        return this.info;
    }

    @Override
    public Integer getState() {
        return this.actuatorState;
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
    public void setValue(final Integer value) {
        this.actuatorState = value;
    }

}
