package homer.common.limit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

final class TestLimit {

    @Test
    void testLimitInt() {
        final int lowerLimit = 10;
        final int upperLimit = 30;
        final int belowRange = lowerLimit - 10;
        final int inRange = 20;
        final int aboveRange = upperLimit + 10;
        assertEquals(lowerLimit, Limit.limit(belowRange, lowerLimit, upperLimit));
        assertEquals(inRange, Limit.limit(inRange, lowerLimit, upperLimit));
        assertEquals(upperLimit, Limit.limit(aboveRange, lowerLimit, upperLimit));
    }

    @Test
    void testLimitDouble() {
        final double lowerLimit = 10.5d;
        final double upperLimit = 30.5d;
        final double belowRange = lowerLimit - 10.0d;
        final double inRange = 24.5d;
        final double aboveRange = upperLimit + 10.0d;
        assertEquals(lowerLimit, Limit.limit(belowRange, lowerLimit, upperLimit));
        assertEquals(inRange, Limit.limit(inRange, lowerLimit, upperLimit));
        assertEquals(upperLimit, Limit.limit(aboveRange, lowerLimit, upperLimit));
    }

}
