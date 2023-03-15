package homer.common.limit;

/**
 * Utility class for numerical range limits.
 */
public final class Limit {

    private Limit() {
        throw new UnsupportedOperationException();
    }

    /**
     * Limits the input between lower and upper limits.
     * 
     * @param input      The input to limit.
     * @param lowerLimit The lower limit.
     * @param upperLimit The upper limit.
     * @return The input limited between the lower and upper limits.
     */
    public static int limit(final int input, final int lowerLimit, final int upperLimit) {
        checkLimitsOrder(lowerLimit, upperLimit);
        return Math.max(lowerLimit, Math.min(input, upperLimit));
    }

    /**
     * Limits the input between lower and upper limits.
     * 
     * @param input      The input to limit.
     * @param lowerLimit The lower limit.
     * @param upperLimit The upper limit.
     * @return The input limited between the lower and upper limits.
     */
    public static double limit(final double input, final double lowerLimit, final double upperLimit) {
        checkLimitsOrder(lowerLimit, upperLimit);
        return Math.max(lowerLimit, Math.min(input, upperLimit));
    }

    /**
     * Limits the input between lower and upper limits.
     * 
     * @param <S>        The comparable numeric type.
     * @param input      The input to limit.
     * @param lowerLimit The lower limit.
     * @param upperLimit The upper limit.
     * @return The input limited between the lower and upper limits.
     */
    public static <S extends Number & Comparable<S>> S limit(final S input, final S lowerLimit, final S upperLimit) {
        checkLimitsOrder(lowerLimit, upperLimit);
        if (input.compareTo(lowerLimit) < 0) {
            return lowerLimit;
        } else if (input.compareTo(upperLimit) > 0) {
            return upperLimit;
        } else {
            return input;
        }
    }

    private static <S extends Number & Comparable<S>> void checkLimitsOrder(final S lowerLimit, final S upperLimit) {
        if (lowerLimit.compareTo(upperLimit) > 0) {
            throw new IllegalArgumentException("lowerLimit and upperLimit are inverted");
        }
    }

}
