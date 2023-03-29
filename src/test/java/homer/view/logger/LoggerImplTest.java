package homer.view.logger;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;

class LoggerImplTest {

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
    void testOutput() {
        final var stringWriter = new StringWriter();
    }

}
