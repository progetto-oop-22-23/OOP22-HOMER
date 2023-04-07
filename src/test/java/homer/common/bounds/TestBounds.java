package homer.common.bounds;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

final class TestBounds {

    private static final int LOWER_BOUND = 0;
    private static final int UPPER_BOUND = 1;
    private final Bounds<Integer> bounds = new Bounds<>(LOWER_BOUND, UPPER_BOUND);

    @Test
    void testLowerBound() {
        assertEquals(LOWER_BOUND, bounds.getLowerBound());
    }

    @Test
    void testUpperBound() {
        assertEquals(UPPER_BOUND, bounds.getUpperBound());
    }

    @Test
    void testException() {
        assertThrowsExactly(InvertedBoundsException.class, () -> new Bounds<>(UPPER_BOUND, LOWER_BOUND));
    }

}
