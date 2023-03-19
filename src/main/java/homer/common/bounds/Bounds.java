package homer.common.bounds;

import java.util.Objects;

/**
 * This class encapsulates the concept of numerical boundaries.
 * 
 * @param <N> The comparable number type.
 */
public final class Bounds<N extends Number & Comparable<N>> {

    private final N lowerBound;
    private final N upperBound;

    /**
     * Constructs a pair of bounds.
     * 
     * @param lowerBound the lower boundary value.
     * @param upperBound the upper boundary value.
     */
    public Bounds(final N lowerBound, final N upperBound) {
        Objects.requireNonNull(lowerBound);
        Objects.requireNonNull(upperBound);
        if (lowerBound.compareTo(upperBound) > 0) {
            throw new InvertedBoundsException();
        }
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Returns the lower boundary value.
     * 
     * @return the lower boundary value.
     */
    public N getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Returns the upper boundary value.
     * 
     * @return the upper boundary value.
     */
    public N getUpperBound() {
        return this.upperBound;
    }

}
