package homer.model.lights;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;

/**
 * Tests Light functionalities.
 */
public final class LightTest {
    private final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false);

    @Test
    
    void testToggle() {
        boolean state = false;
        assertEquals(state, light.getState());
        state = true;
        light.toggle();
        assertEquals(state, light.getState());
    }
}
