package homer.common.time;

import java.time.Duration;

/**
 * Allows floating point conversions for {@link Duration}s.
 */
public class DurationConverter {

    private DurationConverter() {}

    public static double toSeconds(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofSeconds(1));
    }

    public static double toHours(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofHours(1));
    }

    public static double toMinutes(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofMinutes(1));
    }

    public static double toNanos(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofNanos(1));
    }

    public static double toDays(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofDays(1));
    }

    private static double toBase(final Duration deltaTime, final Duration base) {
        return (double) deltaTime.toNanos() / base.toNanos();
    }

}
