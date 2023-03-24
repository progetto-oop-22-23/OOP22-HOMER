package homer.controller.electricalmeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

import homer.api.DeviceIdImpl;
import homer.controller.api.electricalmeter.ElectricalMeter;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;

final class ElectricalMeterTest {
    private final List<Outlet> outlets = new ArrayList<>();
    private static final double STATE = 0;

    @Test
    void testGetOutlets() {

        Collections.addAll(outlets,
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.lOutlet(STATE),
                OutletFactory.lOutlet(STATE)
            );

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

        assertEquals(outlets, meter.getOutlets());
    }

    @Test
    void testSetOutlets() {
        Collections.addAll(outlets,
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.lOutlet(STATE),
                OutletFactory.lOutlet(STATE)
            );

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

        Collections.addAll(outlets,
                OutletFactory.cOutlet(STATE),
                OutletFactory.lOutlet(STATE));
        meter.setOutlets(outlets);

        assertEquals(outlets, meter.getOutlets());
    }

    @Test
    void testAddOutlet() {
        outlets.add(OutletFactory.cOutlet(STATE));
        outlets.add(OutletFactory.lOutlet(STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        final Outlet outlet = OutletFactory.lOutlet(STATE);
        meter.addOutlet(outlet);

        assertEquals(outlet, meter.getOutlets().get(meter.getOutlets().size() - 1));
    }

    @Test
    void testRemoveOutlet() {
        final int outletIndexToRemove = 3;
        final int expectedOutletListSizeAfterRemoval = 3;

        Collections.addAll(outlets,
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.lOutlet(STATE),
                OutletFactory.lOutlet(STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(outlets.size(), meter.getOutlets().size());
        final Outlet toRemove = meter.getOutlets().get(outletIndexToRemove);
        meter.removeOutlet(toRemove);
        assertEquals(expectedOutletListSizeAfterRemoval, meter.getOutlets().size());

        final Throwable removeOutletException = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Outlet not in 'outlets'");
        });
        assertEquals("Outlet not in 'outlets'", removeOutletException.getMessage());

    }

    @Test
    void testCutPowerTo() {
        final int outletIndexToCut = 0;
        final double outletConsumptionValue = 1.0;
        final double expectedConsumptionAfterCut = 0.0;
        outlets.add(OutletFactory.cOutlet(STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);

        meter.getOutlets().get(outletIndexToCut).setState(outletConsumptionValue);

        meter.cutPowerTo(meter.getOutlets().get(outletIndexToCut));

        assertEquals(expectedConsumptionAfterCut, meter.getOutlets().get(outletIndexToCut).getState());
    }

    @Test
    void testCheckConsumption() {
        final int expectedOutletListSize = 5;
        final double outletConsumption = 1.5;
        final double expectedConsumptionBeforeCheck = 7.5;
        final double expectedConsumptionAfterCheck = 3.0;

        assertTrue(outlets.isEmpty());
        Collections.addAll(outlets, 
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE)
        );

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets);
        assertEquals(expectedOutletListSize, meter.getOutlets().size());
        for (final Outlet outlet : meter.getOutlets()) {
            outlet.setState(outletConsumption);
        }

        assertEquals(expectedConsumptionBeforeCheck, meter.getGlobalConsumption());

        meter.checkConsumption();
        assertEquals(expectedConsumptionAfterCheck, meter.getGlobalConsumption());

    }
}
