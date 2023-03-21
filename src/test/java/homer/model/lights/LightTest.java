package homer.model.lights;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceType;

/**
 * Tests Light functionalities.
 */
final class LightTest {
    private final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.LIGHT), false);

    @Test

    void testToggle() {
        boolean state = false;
        assertEquals(state, light.getState());
        state = true;
        light.toggle();
        assertEquals(state, light.getState());
    }
}
