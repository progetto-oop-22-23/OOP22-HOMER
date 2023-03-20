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
     * @param <S>        The comparable type.
     * @param input      The input to limit.
     * @param lowerBound The lower bound.
     * @param upperBound The upper bound.
     * @return The input limited between the lower and upper bounds.
     */
    public static <S extends Comparable<S>> S clamp(final S input, final S lowerBound, final S upperBound) {
        Objects.requireNonNull(input);
        Objects.requireNonNull(lowerBound);
        Objects.requireNonNull(upperBound);
        checkBoundsOrder(lowerBound, upperBound);
        if (input.compareTo(lowerBound) < 0) {
            return lowerBound;
        } else if (input.compareTo(upperBound) > 0) {
            return upperBound;
        } else {
            return input;
        }
    }

    private static <S extends Comparable<S>> void checkBoundsOrder(final S lowerBound, final S upperBound) {
        if (lowerBound.compareTo(upperBound) > 0) {
            throw new IllegalArgumentException("lowerBound and upperBound are inverted");
        }
    }

}
