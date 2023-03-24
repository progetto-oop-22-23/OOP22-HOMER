package homer.model.lights;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;

/**
 * Tests Light functionalities.
 * 
 * @author Alessandro Monticelli
 */
final class LightTest {

    @Test
    void testToggle() {
        final Light light = new Light(false);
        boolean state = false;
        assertEquals(state, light.getState());
        state = true;
        light.toggle();
        assertEquals(state, light.getState());
    }

    @Test
    void testUpdateTickOneHour() {
        final double maxConsumption = 10.0;
        final Light light = new Light(true);
        light.updateTick(Duration.ofMillis(4));
        assertTrue(light.getInstantConsumption() > light.getPowerInfo().getMinConsumption());
        assertTrue(light.getInstantConsumption() <= maxConsumption);
    }
}
