package homer.common.bounds;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

final class TestBounds {

    @Test
    void testException() {
        assertThrowsExactly(InvertedBoundsException.class, () -> new Bounds<>(1, 0));
    }

}
