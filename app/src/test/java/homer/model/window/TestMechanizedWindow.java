package homer.model.window;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TestMechanizedWindow {

    private static final Integer MIN_VALUE = -50;
    private static final Integer MAX_VALUE = 100;
    private final MechanizedWindow window = new MechanizedWindow(MIN_VALUE, MAX_VALUE);

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

    @Test
    void testMinValue() {
        final var minValue = window.getMinValue();
        window.setValue(minValue - MAX_VALUE);
        assertEquals(minValue, window.getState());
    }

    @Test
    void testMaxValue() {
        final var maxValue = window.getMaxValue();
        window.setValue(maxValue + MAX_VALUE);
        assertEquals(maxValue, window.getState());
    }

    private void checkSetValue(final Integer newValue) {
        assertTrue(newValue >= MIN_VALUE && newValue <= MAX_VALUE, "value not in range");
        window.setValue(newValue);
        assertEquals(newValue, window.getValue());
    }

}
