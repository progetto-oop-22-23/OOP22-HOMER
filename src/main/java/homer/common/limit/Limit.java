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
    static int limit(final int input, final int lowerLimit, final int upperLimit) {
        return Math.max(lowerLimit, Math.min(input, upperLimit));
    }

}
