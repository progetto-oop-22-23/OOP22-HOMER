package homer.view.logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

final class LoggerUtilTest {
    private static final Charset STANDARD_CHARSET = Charset.defaultCharset();

    private LoggerUtilTest() {
    }

    public static void assertContains(final ByteArrayOutputStream outputStream, final String string) {
        final var outputStreamToString = outputStream.toString(STANDARD_CHARSET);
        assertTrue(outputStreamToString.contains(string),
                () -> "Expected Output stream" + outputStream + " to contain: " + string);
    }
}
