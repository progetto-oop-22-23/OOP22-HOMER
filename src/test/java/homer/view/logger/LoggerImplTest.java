package homer.view.logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;

import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.model.temperaturechangers.TemperatureChangerType;

class LoggerImplTest {
    private static final TemperatureChangerState AIRCONDITIONING_STATE = new TemperatureChangerState().addTemperatureChangerType(TemperatureChangerType.AIRCONDITIONING);
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
        assertTrue(outputStream.toString().contains("Air conditioning"));
    }

    @Test
    void testHeating() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID,
                new TemperatureChangerState().addTemperatureChangerType(TemperatureChangerType.HEATING));
        assertTrue(outputStream.toString().contains("Heating"));
    }

    @Test
    void testRemoveDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE); 
        logger.removeDevice(DEVICE_ID);
        assertTrue(outputStream.toString().contains("REMOVE DEVICE"));
    }

    @Test
    void testCreateDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        assertTrue(outputStream.toString().contains("ADD DEVICE"));
    }

    @Test
    void testUpdateDevice() {
        final var outputStream  = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        assertTrue(outputStream.toString().contains("UPDATE DEVICE"));
    }
}
