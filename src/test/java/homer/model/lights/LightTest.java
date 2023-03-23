package homer.model.lights;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.PoweredDeviceInfoImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;

/**
 * Tests Light functionalities.
 */
final class LightTest {

    @Test
    void testToggle() {
        final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false);
        boolean state = false;
        assertEquals(state, light.getState());
        state = true;
        light.toggle();
        assertEquals(state, light.getState());
    }

    @Test
    void testUpdateTickOneHour() {
        final double maxConsumption = 10.0;
        final Outlet outlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0.0);
        final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), true,
                new PoweredDeviceInfoImpl(maxConsumption, outlet));
        light.updateTick(Duration.ofMillis(4));
        assertTrue(light.getInstantConsumption() > light.getPowerInfo().getMinConsumption());
        assertTrue(light.getInstantConsumption() <= maxConsumption);
    }
}
