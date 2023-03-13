package homer.controller.electricalmeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import homer.DeviceInfoImpl;
import homer.api.DeviceIdImpl;
import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactoryImpl;

final class ElectricalMeterTest {
    private final List<Outlet> outlets = new ArrayList<>();
    private static final String LOUTLET = "LOUTLET";
    private static final String COUTLET = "COUTLET";
    @Test
    void testGetOutlets() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(outlets, meter.getOutlets());
    }

    @Test
    void testSetOutlets() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));

        meter.setOutlets(outlets);

        assertEquals(outlets, meter.getOutlets());
    }

    @Test
    void testAddOutlet() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        final Outlet outlet = new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0);
        meter.addOutlet(outlet);

        assertEquals(outlet, meter.getOutlets().get(meter.getOutlets().size() - 1));
    }

    @Test
    void testRemoveOutlet() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), 0));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(outlets.size(), meter.getOutlets().size());
        Outlet toRemove = meter.getOutlets().get(3);
        meter.removeOutlet(toRemove);
        assertEquals(3, meter.getOutlets().size());

        Throwable removeOutletException = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException(toRemove.getInfo() + " not in 'outlets'");
        });
        assertEquals(toRemove.getInfo() + " not in 'outlets'", removeOutletException.getMessage());

    }

    @Test
    void testCutPowerTo() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

        meter.getOutlets().get(0).setState(1.0);

        meter.cutPowerTo(meter.getOutlets().get(0));

        assertEquals(0.0, meter.getOutlets().get(0).getState());
    }

    @Test
    void testCheckConsumption() {
        double 
        assertEquals(true, outlets.isEmpty());
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));

        assertEquals(3, outlets.size());
        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(3, meter.getOutlets().size());
        for (Outlet outlet : meter.getOutlets()) {
            outlet.setState(2.0);
        }

        assertEquals(6.0, meter.getGlobalConsumption());

        meter.checkConsumption();
        assertEquals(2.0, meter.getGlobalConsumption());

        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), 0));

        meter.setOutlets(outlets);

        assertEquals(5, outlets.size());

        for (Outlet outlet : meter.getOutlets()) {
            outlet.setState(2.0);
        }

        assertEquals(10.0, meter.getGlobalConsumption());

        meter.checkConsumption();
        assertEquals(2.0, meter.getGlobalConsumption());

    }
}
