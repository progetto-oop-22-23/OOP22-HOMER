package homer.common.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class TestDurationConverter {
    private static final double DELTA = 0.0001d;
    private static final long FIVE_HUNDRED = 500;

    @Test
    void testSeconds() {
        assertEquals(1, DurationConverter.toSeconds(Duration.ofSeconds(1)), DELTA);
        assertEquals(0.5, DurationConverter.toSeconds(Duration.ofMillis(FIVE_HUNDRED)), DELTA);
    }

    void testHours() {
        assertEquals(1, DurationConverter.toHours(Duration.ofHours(1)), DELTA);
        assertEquals(0.5, DurationConverter.toHours(Duration.ofMinutes(30)), DELTA);
    }
}
