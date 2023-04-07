package homer.view.logger;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;

import homer.api.DeviceIdImpl;

class WarningLoggerTest {

    @Test
    void testWarning() {
        final var outputStream = new ByteArrayOutputStream();
        final var logger = new WarningLogger(new LoggerImpl(outputStream));
        logger.removeDevice(new DeviceIdImpl());
        LoggerUtilTest.assertContains(outputStream, "WARNING"); 
    }
}
