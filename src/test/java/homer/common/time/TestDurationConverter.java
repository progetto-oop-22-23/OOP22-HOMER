package homer.common.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;

class TestDurationConverter {
    private static final double DELTA = 0.0001d;
    private static final long FIVE_HUNDRED = 500;
    private static final double ONE_THIRD = 1 / 3d;
    private static final long TWENTY = 20;
    private static final long THIRTY = 30;
    private static final long TWELVE = 12;
    private static final long SECONDS_IN_A_DAY = 86_400;
    private static final long ONE_THOUSAND = 1000;

    @Test
    void testSeconds() {
        assertEquals(1, DurationConverter.toSeconds(Duration.ofSeconds(1)), DELTA);
        assertEquals(0.5, DurationConverter.toSeconds(Duration.ofMillis(FIVE_HUNDRED)), DELTA);
        assertEquals(0, DurationConverter.toSeconds(Duration.ofSeconds(0)), DELTA);
    }

    @Test
    void testHours() {
        assertEquals(1, DurationConverter.toHours(Duration.ofHours(1)), DELTA);
        assertEquals(0.5, DurationConverter.toHours(Duration.ofMinutes(THIRTY)), DELTA);
        assertEquals(0, DurationConverter.toSeconds(Duration.ofSeconds(0)), DELTA);
    }

    @Test
    void testMinutes() {
        assertEquals(1, DurationConverter.toMinutes(Duration.ofMinutes(1)), DELTA);
        assertEquals(ONE_THIRD, DurationConverter.toMinutes(Duration.ofSeconds(TWENTY)), DELTA);
    }

    @Test
    void testDays() {
        assertEquals(0.5, DurationConverter.toDays(Duration.ofHours(TWELVE)));
        assertEquals(1, DurationConverter.toDays(Duration.ofSeconds(SECONDS_IN_A_DAY)));
    }

    @Test
    void testMillis() {
        assertEquals(ONE_THOUSAND, DurationConverter.toMillis(Duration.ofSeconds(1)));
        assertEquals(1d / ONE_THOUSAND, DurationConverter.toMillis(Duration.ofNanos(ONE_THOUSAND)), DELTA);
    }

}
