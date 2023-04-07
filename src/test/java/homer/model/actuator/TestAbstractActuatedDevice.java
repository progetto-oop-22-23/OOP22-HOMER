package homer.model.actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.Test;

import homer.api.DeviceState;
import homer.api.state.ActuatedDeviceState;
import homer.common.bounds.Bounds;

final class TestAbstractActuatedDevice {

    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 100;
    private static final Bounds<Integer> BOUNDS = new Bounds<>(LOWER_BOUND, UPPER_BOUND);
    private final Actuator actuator = new SimpleActuator(BOUNDS);
    private final AbstractActuatedDevice device = new AbstractActuatedDevice(actuator, "Generic");

    @Test
    void testGetState() {
        assertNotNull(device.getState());
        assertTrue(device.getState().getPositionBounds().isPresent());
    }

    @Test
    void testSetState() {
        final var validPosition = (LOWER_BOUND + UPPER_BOUND) / 2;
        final var invalidNewBounds = new Bounds<>(0, 50);
        final var newState = new ActuatedDeviceState(validPosition, invalidNewBounds, "");
        device.setState(newState);
        device.updateTick(Duration.ofNanos(1));
        assertEquals(validPosition, device.getState().getPosition());
        assertEquals(BOUNDS, device.getState().getPositionBounds().get()); // bounds must be unchanged
    }

    @Test
    void testIllegalState() {
        assertThrowsExactly(IllegalArgumentException.class, () -> device.setState(new DeviceState() {
        }));
    }
}
