package homer.common.limit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// CHECKSTYLE: MagicNumber OFF
// Rule disabled in test class.

final class TestLimit {

    @Test
    void testLimitInt() {
        final int lowerLimit = 10;
        final int upperLimit = 30;
        assertEquals(lowerLimit, Limit.limit(lowerLimit - 10, lowerLimit, upperLimit));
        assertEquals(20, Limit.limit(20, lowerLimit, upperLimit));
        assertEquals(upperLimit, Limit.limit(upperLimit + 10, lowerLimit, upperLimit));
    }

    @Test
    void testLimitDouble() {
        final double lowerLimit = 10.5d;
        final double upperLimit = 30.5d;
        assertEquals(lowerLimit, Limit.limit(lowerLimit - 10.0d, lowerLimit, upperLimit));
        assertEquals(24.5d, Limit.limit(24.5d, lowerLimit, upperLimit));
        assertEquals(upperLimit, Limit.limit(upperLimit + 10.0d, lowerLimit, upperLimit));
    }

}
