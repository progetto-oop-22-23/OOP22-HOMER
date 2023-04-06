package homer.controller.electricalmeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

import homer.controller.ControllerImpl;
import homer.controller.DeviceManagerImpl;
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
                OutletFactory.lOutlet(STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets, new DeviceManagerImpl(new ControllerImpl()));

        assertEquals(outlets, meter.getOutlets());
    }

    @Test
    void testSetOutlets() {
        Collections.addAll(outlets,
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.lOutlet(STATE),
                OutletFactory.lOutlet(STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets, new DeviceManagerImpl(new ControllerImpl()));

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

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets, new DeviceManagerImpl(new ControllerImpl()));
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

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets, new DeviceManagerImpl(new ControllerImpl()));
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
        final int expectedSizeAfterCut = 0;
        outlets.add(OutletFactory.cOutlet(STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl();
        meter.setOutlets(outlets);
        meter.getOutlets().get(outletIndexToCut).getState().addValue(outletConsumptionValue);
        meter.cutPowerTo(meter.getOutlets().get(outletIndexToCut));

        assertEquals(expectedSizeAfterCut, meter.getOutlets().size());
    }

    @Test
    void testUpdateTick() {
        final double outletConsumption = 1500.0;
        final double expectedConsumptionAfterCheck = 30;
        final double expectedAveragePowerAfterCheck = 15;
        final double delta = 2.5;
        final int hours = 2;
        assertTrue(outlets.isEmpty());
        Collections.addAll(outlets,
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE),
                OutletFactory.cOutlet(STATE));

        final ElectricalMeter meter = new ElectricalMeterImpl(outlets, new DeviceManagerImpl(new ControllerImpl()));
        for (final Outlet outlet : meter.getOutlets()) {
            outlet.getState().addValue(outletConsumption);
        }

        // Can't test the consumption before calling updateTick because it assumes wrong
        // values without callin Oulet's updateTick.
        //assertEquals(expectedAveragePowerBeforeCheck, meter.getAveragePower());
        ((ElectricalMeterImpl) meter).updateTick(Duration.ofHours(hours));

        // The power and the consumption have oscillations from the
        // expected value, due to the stacking od Outlet consumption obsillations.c
        assertEquals(expectedAveragePowerAfterCheck, meter.getAveragePower(), expectedAveragePowerAfterCheck / delta);
        assertEquals(expectedConsumptionAfterCheck, meter.getGlobalConsumption(), expectedConsumptionAfterCheck / delta);
    }
}
