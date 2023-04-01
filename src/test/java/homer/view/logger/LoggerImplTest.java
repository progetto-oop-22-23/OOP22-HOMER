package homer.view.logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

import homer.api.DeviceId;
import homer.api.DeviceIdImpl;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.model.temperaturechangers.TemperatureChangerType;

class LoggerImplTest {
    private static final TemperatureChangerState AIRCONDITIONING_STATE = new TemperatureChangerState()
            .addTemperatureChangerType(TemperatureChangerType.AIRCONDITIONING).addCurrentIntensity(10);
    private static final DeviceId DEVICE_ID = new DeviceIdImpl();
    private static final Charset STANDARD_CHARSET = Charset.defaultCharset();

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
        assertContains(outputStream, "Air conditioning");
    }

    @Test
    void testHeating() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID,
                new TemperatureChangerState().addTemperatureChangerType(TemperatureChangerType.HEATING)
                        .addCurrentIntensity(10));
        assertContains(outputStream, "Heating");
    }

    @Test
    void testRemoveDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        logger.removeDevice(DEVICE_ID);
        assertContains(outputStream, "REMOVE DEVICE");
    }

    @Test
    void testCreateDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        assertContains(outputStream, "ADD DEVICE");
    }

    @Test
    void testUpdateDevice() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new LoggerImpl(outputStream);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        logger.updateDeviceState(DEVICE_ID, AIRCONDITIONING_STATE);
        assertContains(outputStream, "UPDATE DEVICE");
    }

    private void assertContains(final ByteArrayOutputStream outputStream, final String string) {
        final var outputStreamToString = outputStream.toString(STANDARD_CHARSET);
        assertTrue(outputStreamToString.contains(string),
                () -> "Expected Output stream" + outputStream + " to contain: " + string);
    }
}
