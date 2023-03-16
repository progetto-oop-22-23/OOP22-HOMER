package homer.common.limit;

import java.util.Objects;

/**
 * Utility class for range limits.
 */
public final class Limit {

    private Limit() {
        throw new UnsupportedOperationException();
    }

    /**
     * Limits the input between lower and upper bounds.
     * 
     * @param input      The input to limit.
     * @param lowerBound The lower bound.
     * @param upperBound The upper bound.
     * @return The input limited between the lower and upper bounds.
     */
    public static int limit(final int input, final int lowerBound, final int upperBound) {
        checkLimitsOrder(lowerBound, upperBound);
        return Math.max(lowerBound, Math.min(input, upperBound));
    }

    /**
     * Limits the input between lower and upper bounds.
     * 
     * @param input      The input to limit.
     * @param lowerBound The lower bound.
     * @param upperBound The upper bound.
     * @return The input limited between the lower and upper bounds.
     */
    public static double limit(final double input, final double lowerBound, final double upperBound) {
        checkLimitsOrder(lowerBound, upperBound);
        return Math.max(lowerBound, Math.min(input, upperBound));
    }

    /**
     * Limits the input between lower and upper bounds.
     * 
     * @param <S>        The comparable type.
     * @param input      The input to limit.
     * @param lowerBound The lower bound.
     * @param upperBound The upper bound.
     * @return The input limited between the lower and upper bounds.
     */
    public static <S extends Comparable<S>> S limit(final S input, final S lowerBound, final S upperBound) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(lowerBound);
        Objects.requireNonNull(upperBound);
        checkLimitsOrder(lowerBound, upperBound);
        if (input.compareTo(lowerBound) < 0) {
            return lowerBound;
        } else if (input.compareTo(upperBound) > 0) {
            return upperBound;
        } else {
            return input;
        }
    }

    private static <S extends Comparable<S>> void checkLimitsOrder(final S lowerBound, final S upperBound) {
        if (lowerBound.compareTo(upperBound) > 0) {
            throw new IllegalArgumentException("lowerBound and upperBound are inverted");
        }
    }

}
