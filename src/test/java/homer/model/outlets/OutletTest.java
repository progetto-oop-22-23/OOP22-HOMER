package homer.model.outlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.Optional;
import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.PoweredDeviceInfoImpl;
import homer.model.lights.Light;

final class OutletTest {

    @Test
    void testSetState() {
        final double expectedCoutletState = 1.0;
        final double expectedLoutletState = 2.5;
        final int milliseconds = 6;

        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        final Outlet lOutlet = OutletFactory.lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "LOUTLET"), 0);
        cOutlet.setState(expectedCoutletState);
        assertEquals(expectedCoutletState, cOutlet.getState());
        lOutlet.setState(expectedLoutletState);
        assertEquals(expectedLoutletState, lOutlet.getState());

        final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), true,
                new PoweredDeviceInfoImpl(10.0, lOutlet));
        lOutlet.setState(0.0);
        lOutlet.plug(light);
        light.updateTick(Duration.ofMillis(milliseconds));
        lOutlet.setState();
        assertTrue(lOutlet.getState() < lOutlet.getMaxValue());
        assertEquals(light.getInstantConsumption(), lOutlet.getState());
    }

    @Test
    void testPlug() {
        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false,
                new PoweredDeviceInfoImpl(10, cOutlet));
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        assertEquals(light.getInfo(), cOutlet.getDevice().get().getInfo());
        cOutlet.plug(null);
        assertEquals(Optional.empty(), cOutlet.getDevice());
    }

    @Test
    void testUnplug() {
        final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false);
        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        cOutlet.unplug();
        assertEquals(Optional.empty(), cOutlet.getDevice());
        assertEquals(0.0, cOutlet.getState());
    }
}
