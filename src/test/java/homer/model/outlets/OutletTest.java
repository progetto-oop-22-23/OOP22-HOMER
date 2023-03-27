package homer.model.outlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.Optional;
import homer.api.PoweredDeviceInfoImpl;
import homer.model.lights.Light;

final class OutletTest {

    @Test
    void testSetValue() {
        final double expectedCoutletState = 1.0;
        final double expectedLoutletState = 2.5;

        final Outlet cOutlet = OutletFactory.cOutlet(0);
        final Outlet lOutlet = OutletFactory.lOutlet(0);
        cOutlet.getState().addValue(expectedCoutletState);
        assertEquals(expectedCoutletState, cOutlet.getState().getPower().get());
        lOutlet.getState().addValue(expectedLoutletState);
        assertEquals(expectedLoutletState, lOutlet.getState().getPower().get());
    }

    @Test
    void testUpdateTickOneHour() {
        final int milliseconds = 8;
        final int hours = 2;
        final double delta = 0.001;
        final Outlet lOutlet = OutletFactory.lOutlet(0);
        final Light light = new Light(true, new PoweredDeviceInfoImpl(10.0, lOutlet));
        lOutlet.getState().addValue(0.0);
        lOutlet.plug(light);
        light.updateTick(Duration.ofMillis(milliseconds));
        lOutlet.updateTick(Duration.ofHours(hours));

        assertTrue(lOutlet.getState().getPower().get() > lOutlet.getState().getMin().get());
        assertTrue(lOutlet.getState().getPower().get() < lOutlet.getState().getMax().get());
        assertEquals(light.getInstantConsumption() * hours, lOutlet.getState().getPower().get(), delta);
    }

    @Test
    void testPlug() {
        final Outlet cOutlet = OutletFactory.cOutlet(0);
        final Light light = new Light(false);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        cOutlet.plug(null);
        assertEquals(Optional.empty(), cOutlet.getDevice());
    }

    @Test
    void testUnplug() {
        final Light light = new Light(false);
        final Outlet cOutlet = OutletFactory.cOutlet(0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        cOutlet.unplug();
        assertEquals(Optional.empty(), cOutlet.getDevice());
        assertEquals(0.0, cOutlet.getState().getPower().get());
    }
}
