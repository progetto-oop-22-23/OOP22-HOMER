package homer.common.bounds;

import java.util.Objects;

/**
 * This class encapsulates the concept of range boundaries on comparable objects.
 * 
 * @param <N> The comparable object type.
 */
public final class Bounds<N extends Comparable<N>> {

    private final N lowerBound;
    private final N upperBound;

    /**
     * Constructs a pair of bounds.
     * 
     * @param lowerBound the lower boundary object.
     * @param upperBound the upper boundary object.
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
     * Returns the lower boundary object.
     * 
     * @return the lower boundary object.
     */
    public N getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Returns the upper boundary object.
     * 
     * @return the upper boundary object.
     */
    public N getUpperBound() {
        return this.upperBound;
    }

}
