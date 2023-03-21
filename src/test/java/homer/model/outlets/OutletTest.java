package homer.model.outlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.api.DeviceType;

import homer.model.lights.Light;

final class OutletTest {

    @Test
    void testSetState() {
        final double expectedCoutletState = 1.0;
        final double expectedLoutletState = 2.5;
        final double positiveCoutletStateOverMax = 2.5;
        final double positiveLoutletStateOverMax = 4.0;
        final double negativeCoutletStateUnderMin = -2.5;
        final double negativeLoutletStateUnderMin = -4.0;

        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.COUTLET), 0);
        final Outlet lOutlet = OutletFactory.lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.LOUTLET), 0);
        cOutlet.setState(expectedCoutletState);
        assertEquals(expectedCoutletState, cOutlet.getState());
        lOutlet.setState(expectedLoutletState);
        assertEquals(expectedLoutletState, lOutlet.getState());

        Throwable cOutletException = assertThrows(IllegalArgumentException.class, () -> {
            cOutlet.setState(positiveCoutletStateOverMax);
        });

        assertEquals("Value must be positive and < 2.0", cOutletException.getMessage());

        Throwable lOutletException = assertThrows(IllegalArgumentException.class, () -> {
            lOutlet.setState(positiveLoutletStateOverMax);
        });

        assertEquals("Value must be positive and < 3.5", lOutletException.getMessage());

        cOutletException = assertThrows(IllegalArgumentException.class, () -> {
            cOutlet.setState(negativeCoutletStateUnderMin);
        });

        assertEquals("Value must be positive and < 2.0", cOutletException.getMessage());

        lOutletException = assertThrows(IllegalArgumentException.class, () -> {
            lOutlet.setState(negativeLoutletStateUnderMin);
        });

        assertEquals("Value must be positive and < 3.5", lOutletException.getMessage());
    }

    @Test
    void testPlug() {
        final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.LIGHT), false);
        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.LOUTLET), 0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        assertEquals(light.getInfo(), cOutlet.getDevice().get().getInfo());
        cOutlet.plug(null);
        assertEquals(Optional.empty(), cOutlet.getDevice());
    }

    @Test
    void testUnplug() {
        final Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.LIGHT), false);
        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), DeviceType.COUTLET), 0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        cOutlet.unplug();
        assertEquals(Optional.empty(), cOutlet.getDevice());
        assertEquals(0.0, cOutlet.getState());
    }
}
