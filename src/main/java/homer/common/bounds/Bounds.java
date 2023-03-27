package homer.common.bounds;

import java.util.Objects;

/**
 * This class encapsulates the concept of range boundaries on comparable objects.
 * 
 * @param <T> The comparable object type.
 */
public final class Bounds<T extends Comparable<T>> {

    private final T lowerBound;
    private final T upperBound;

    /**
     * Constructs a pair of bounds.
     * 
     * @param lowerBound the lower boundary object.
     * @param upperBound the upper boundary object.
     */
    public Bounds(final T lowerBound, final T upperBound) {
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
    public T getLowerBound() {
        return this.lowerBound;
    }

    /**
     * Returns the upper boundary object.
     * 
     * @return the upper boundary object.
     */
    public T getUpperBound() {
        return this.upperBound;
    }

}
