package homer.model.outlets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.model.lights.Light;

public final class OutletTest {

    private final OutletFactory factory = new OutletFactoryImpl();

    @Test
    void testSetValue() {
        double cOutletValue = 1.0;
        double lOutletValue = 2.5;
        final Outlet cOutlet = factory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        final Outlet lOutlet = factory.lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "LOUTLET"), 0);
        cOutlet.setValue(cOutletValue);
        assertEquals(cOutletValue, cOutlet.getValue());
        lOutlet.setValue(lOutletValue);
        assertEquals(lOutletValue, lOutlet.getValue());

        cOutletValue = 2.5;
        lOutletValue = 4.0;

        Throwable cOutletException = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Value must be positive and < " + cOutlet.getMaxValue());
        });

        assertEquals("Value must be positive and < 2.0", cOutletException.getMessage());

        Throwable lOutletException = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Value must be positive and < " + lOutlet.getMaxValue());
        });

        assertEquals("Value must be positive and < 3.5", lOutletException.getMessage());

        cOutletValue = -2.5;
        lOutletValue = -4.0;

        cOutletException = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Value must be positive and < " + cOutlet.getMaxValue());
        });

        assertEquals("Value must be positive and < 2.0", cOutletException.getMessage());
        lOutletException = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Value must be positive and < " + lOutlet.getMaxValue());
        });

        assertEquals("Value must be positive and < 3.5", lOutletException.getMessage());
    }

    @Test
    void testPlug() {
        Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false);
        final Outlet cOutlet = factory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        assertEquals(light.getInfo(), cOutlet.getDevice().get().getInfo());
        cOutlet.plug(null);
        assertEquals(Optional.empty(), cOutlet.getDevice());
    }

    @Test
    void testUnplug() {
        Light light = new Light(new DeviceInfoImpl(new DeviceIdImpl(), "LIGHT"), false);
        final Outlet cOutlet = factory.cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), "COUTLET"), 0);
        cOutlet.plug(light);
        assertEquals(light, cOutlet.getDevice().get());
        cOutlet.unplug();
        assertEquals(Optional.empty(), cOutlet.getDevice());
        assertEquals(0.0, cOutlet.getValue());
    }
}
