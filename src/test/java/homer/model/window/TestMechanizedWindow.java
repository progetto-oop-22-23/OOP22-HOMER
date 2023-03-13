package homer.model.window;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import homer.api.DeviceInfo;

// CHECKSTYLE: MagicNumber OFF
// Rule disabled in test suite.

final class TestMechanizedWindow {

    private static final int MIN_VALUE = -50;
    private static final int MAX_VALUE = 100;
    private final MechanizedWindow window = new MechanizedWindow(MIN_VALUE, MAX_VALUE);

    @Test
    void testGetInfo() {
        final DeviceInfo info = this.window.getInfo();
        assertNotNull(info);
        assertNotNull(info.getID());
        assertEquals(MechanizedWindow.DEVICE_TYPE, info.getType());
    }

    @Test
    void testGetState() {
        final var currentState = window.getState();
        assertNotNull(currentState);
        assertTrue(currentState >= MIN_VALUE && currentState <= MAX_VALUE);
    }

    @Test
    void testSetState() {
        checkSetValue(50);
        checkSetValue(100);
    }

    /**
     * Test for the position lower limit.
     */
    @Test
    void testMinValue() {
        final var minValue = window.getMinValue();
        window.setState(minValue - Math.abs(MAX_VALUE));
        window.updateTick(Duration.ofMillis(1));
        assertEquals(minValue, window.getState());
    }

    /**
     * Test for the position upper limit.
     */
    @Test
    void testMaxValue() {
        final var maxValue = window.getMaxValue();
        window.setState(maxValue + Math.abs(MAX_VALUE));
        window.updateTick(Duration.ofMillis(1));
        assertEquals(maxValue, window.getState());
    }

    /**
     * Test for the correct order of the lower and upper limits.
     */
    @Test
    void testMinMax() {
        assertTrue(window.getMinValue() <= window.getMaxValue());
        assertThrowsExactly(IllegalArgumentException.class, () -> new MechanizedWindow(MAX_VALUE, MIN_VALUE));
    }

    private void checkSetValue(final int newValue) {
        assertTrue(newValue >= MIN_VALUE && newValue <= MAX_VALUE, "value not in range");
        window.setState(newValue);
        window.updateTick(Duration.ofMillis(1));
        assertEquals(newValue, window.getState());
    }

}
