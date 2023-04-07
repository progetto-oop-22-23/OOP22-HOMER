package homer.model.lights;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        assertFalse(light.getState().isOn());
        light.toggle();
        assertTrue(light.getState().isOn());
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
