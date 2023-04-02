package homer.view.logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.model.temperaturechangers.TemperatureChangerState;

class LoggerImplTest {
    private static final TemperatureChangerState AIRCONDITIONING_STATE = new TemperatureChangerState()
            .addTemperatureChangerType("AIR CONDITIONING").addCurrentIntensity(10);
    private static final DeviceId DEVICE_ID = new DeviceIdImpl();

    @Test
    void testEmptyOutputStream() {
        assertThrowsExactly(NullPointerException.class, () -> new LoggerImpl(null));
        final var logger = new LoggerImpl(System.out);
        assertThrowsExactly(NullPointerException.class, () -> logger.setOutputStream(null));
    }

    @Test
    void testNonEmptyOutputStream() {
        assertDoesNotThrow(() -> new LoggerImpl(new ByteArrayOutputStream()));
        final var logger = new LoggerImpl(System.out);
        assertDoesNotThrow(() -> logger.setOutputStream(new ByteArrayOutputStream()));
    }

    @Test
    void testAirconditioning() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        TestLoggerUtil.assertContains(outputStream, "Air conditioning");
    }

    @Test
    void testHeating() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID,
                new TemperatureChangerState().addTemperatureChangerType("HEATING")
                        .addCurrentIntensity(10));
        TestLoggerUtil.assertContains(outputStream, "Heating");
    }

    @Test
    void testRemoveDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        logger.removeDevice(DEVICE_ID);
        TestLoggerUtil.assertContains(outputStream, "REMOVE DEVICE");
    }

    @Test
    void testCreateDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        TestLoggerUtil.assertContains(outputStream, "ADD DEVICE");
    }

    @Test
    void testUpdateDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        TestLoggerUtil.assertContains(outputStream, "UPDATE DEVICE");
    }

}
