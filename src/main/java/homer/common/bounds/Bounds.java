package homer.common.bounds;

/**
 * This class encapsulates the concept of numerical boundaries.
 * 
 * @param <N> The comparable number type.
 */
public final class Bounds<N extends Number & Comparable<N>> {

    private final N lower;
    private final N upper;

    /**
     * Constructs a pair of bounds.
     * 
     * @param lower the lower boundary value.
     * @param upper the upper boundary value.
     */
    public Bounds(final N lower, final N upper) {
        if (lower.compareTo(upper) > 0) {
            throw new InvertedBoundsException();
        }
        this.lower = lower;
        this.upper = upper;
    }

    /**
     * Returns the lower boundary value.
     * 
     * @return the lower boundary value.
     */
    public N getLower() {
        return this.lower;
    }

    /**
     * Returns the upper boundary value.
     * 
     * @return the upper boundary value.
     */
    public N getUpper() {
        return this.upper;
    }

}
