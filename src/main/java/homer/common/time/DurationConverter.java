package homer.common.time;

import java.time.Duration;

/**
 * Allows floating point conversions for {@link Duration}s.
 */
public final class DurationConverter {

    private DurationConverter() {
    }

    /**
     * 
     * @param deltaTime the duration to be converted
     * @return The delta converted to seconds.
     */
    public static double toSeconds(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofSeconds(1));
    }

    /**
     * 
     * @param deltaTime the duration to be converted
     * @return The delta converted to hours.
     */
    public static double toHours(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofHours(1));
    }

    /**
     * 
     * @param deltaTime the duration to be converted
     * @return The delta converted to minutes.
     */
    public static double toMinutes(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofMinutes(1));
    }

    /**
     * 
     * @param deltaTime the duration to be converted
     * @return The delta converted to milliseconds.
     */
    public static double toMillis(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofMillis(1));
    }

    /**
     * 
     * @param deltaTime the duration to be converted
     * @return The delta converted to days.
     */
    public static double toDays(final Duration deltaTime) {
        return toBase(deltaTime, Duration.ofDays(1));
    }

    private static double toBase(final Duration deltaTime, final Duration base) {
        return (double) deltaTime.toNanos() / base.toNanos();
    }

}
