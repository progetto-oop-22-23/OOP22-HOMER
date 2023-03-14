package homer.model.outlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.model.lights.Light;

final class OutletTest {

    @Test
    void testSetState() {
        final double EXPECTED_COUTLET_VALUE = 1.0;
        final double EXPECTED_LOUTLET_VALUE = 2.5;
        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        final Outlet lOutlet = OutletFactory.lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "LOUTLET"), 0);
        cOutlet.setState(EXPECTED_COUTLET_VALUE);
        assertEquals(EXPECTED_COUTLET_VALUE, cOutlet.getState());
        lOutlet.setState(EXPECTED_LOUTLET_VALUE);
        assertEquals(EXPECTED_LOUTLET_VALUE, lOutlet.getState());

        Throwable cOutletException = assertThrows(IllegalArgumentException.class, () -> {
            final double POSITIVE_COUTLET_STATE_OVER_MAX = 2.5;
            cOutlet.setState(POSITIVE_COUTLET_STATE_OVER_MAX);
        });

        assertEquals("Value must be positive and < 2.0", cOutletException.getMessage());

        Throwable lOutletException = assertThrows(IllegalArgumentException.class, () -> {
            final double POSITIVE_LOUTLET_STATE_OVER_MAX = 4.0;
            lOutlet.setState(POSITIVE_LOUTLET_STATE_OVER_MAX);
        });

        assertEquals("Value must be positive and < 3.5", lOutletException.getMessage());

        cOutletException = assertThrows(IllegalArgumentException.class, () -> {
            final double NEGATIVE_COUTLET_STATE_UNDER_MIN = -2.5;
            cOutlet.setState(NEGATIVE_COUTLET_STATE_UNDER_MIN);
        });

        assertEquals("Value must be positive and < 2.0", cOutletException.getMessage());

        lOutletException = assertThrows(IllegalArgumentException.class, () -> {
            final double NEGATIVE_LOUTLET_STATE_UNDER_MIN = -4.0;
            lOutlet.setState(NEGATIVE_LOUTLET_STATE_UNDER_MIN);
        });

        assertEquals("Value must be positive and < 3.5", lOutletException.getMessage());
    }

    @Test
    void testPlug() {
        Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false);
        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        assertEquals(light.getInfo(), cOutlet.getDevice().get().getInfo());
        cOutlet.plug(null);
        assertEquals(Optional.empty(), cOutlet.getDevice());
    }

    @Test
    void testUnplug() {
        final double EXPECTED_OUTLET_STATE_AFTER_UNPLUG = 0.0;
        Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false);
        final Outlet cOutlet = OutletFactory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        cOutlet.unplug();
        assertEquals(Optional.empty(), cOutlet.getDevice());
        assertEquals(EXPECTED_OUTLET_STATE_AFTER_UNPLUG, cOutlet.getState());
    }
}
