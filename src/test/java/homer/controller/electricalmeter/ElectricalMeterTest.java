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
    private static final double STATE = 0;
    private static final int OUTLET_INDEX_TO_REMOVE = 3;
    private static final int EXPECTED_OUTLET_LIST_SIZE = 3;
    private static final int OUTLET_INDEX_TO_CUT = 0;
    private static final double OUTLET_CONSUMPTION_VALUE = 1.0;
    private static final double EXPECTED_CONSUMPTION_AFTER_CUT = 0.0;
    private static final int EXPECTED_OUTLET_LIST_SIZE_ = 5;
    private static final double EXPECTED_CONSUMPTION_BEFORE_CHECK = 10.0;
    private static final double EXPECTED_CONSUMPTION_AFTER_CHECK = 2.0;
    private static final double OUTLET_CONSUMPTION = 2.0;

    @Test
    void testGetOutlets() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(outlets, meter.getOutlets());
    }

    @Test
    void testSetOutlets() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));

        meter.setOutlets(outlets);

        assertEquals(outlets, meter.getOutlets());
    }

    @Test
    void testAddOutlet() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        final Outlet outlet = new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE);
        meter.addOutlet(outlet);

        assertEquals(outlet, meter.getOutlets().get(meter.getOutlets().size() - 1));
    }

    @Test
    void testRemoveOutlet() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));
        outlets.add(new OutletFactoryImpl().lOutlet(new DeviceInfoImpl(new DeviceIdImpl(), LOUTLET), STATE));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(outlets.size(), meter.getOutlets().size());
        Outlet toRemove = meter.getOutlets().get(OUTLET_INDEX_TO_REMOVE);
        meter.removeOutlet(toRemove);
        assertEquals(EXPECTED_OUTLET_LIST_SIZE, meter.getOutlets().size());

        Throwable removeOutletException = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException(toRemove.getInfo() + " not in 'outlets'");
        });
        assertEquals(toRemove.getInfo() + " not in 'outlets'", removeOutletException.getMessage());

    }

    @Test
    void testCutPowerTo() {
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

        meter.getOutlets().get(OUTLET_INDEX_TO_CUT).setState(OUTLET_CONSUMPTION_VALUE);

        meter.cutPowerTo(meter.getOutlets().get(OUTLET_INDEX_TO_CUT));

        assertEquals(EXPECTED_CONSUMPTION_AFTER_CUT, meter.getOutlets().get(OUTLET_INDEX_TO_CUT).getState());
    }

    @Test
    void testCheckConsumption() {

        assertEquals(true, outlets.isEmpty());
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));
        outlets.add(new OutletFactoryImpl().cOutlet(new DeviceInfoImpl(new DeviceIdImpl(), COUTLET), STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(EXPECTED_OUTLET_LIST_SIZE, meter.getOutlets().size());
        for (Outlet outlet : meter.getOutlets()) {
            outlet.setState(OUTLET_CONSUMPTION);
        }

        assertEquals(EXPECTED_CONSUMPTION_BEFORE_CHECK, meter.getGlobalConsumption());

        meter.checkConsumption();
        assertEquals(EXPECTED_CONSUMPTION_AFTER_CHECK, meter.getGlobalConsumption());

    }
}
