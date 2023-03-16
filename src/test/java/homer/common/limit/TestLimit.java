package homer.common.limit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

final class TestLimit {

    @Test
    void testLimitInt() {
        final int lowerBound = 10;
        final int upperBound = 30;
        final int belowRange = lowerBound - 10;
        final int inRange = 20;
        final int aboveRange = upperBound + 10;
        assertEquals(lowerBound, Limit.limit(belowRange, lowerBound, upperBound));
        assertEquals(inRange, Limit.limit(inRange, lowerBound, upperBound));
        assertEquals(upperBound, Limit.limit(aboveRange, lowerBound, upperBound));
    }

    @Test
    void testLimitDouble() {
        final double lowerBound = 10.5d;
        final double upperBound = 30.5d;
        final double belowRange = lowerBound - 10.0d;
        final double inRange = 24.5d;
        final double aboveRange = upperBound + 10.0d;
        assertEquals(lowerBound, Limit.limit(belowRange, lowerBound, upperBound));
        assertEquals(inRange, Limit.limit(inRange, lowerBound, upperBound));
        assertEquals(upperBound, Limit.limit(aboveRange, lowerBound, upperBound));
    }

    @Test
    void testLimitFloat() {
        final float lowerBound = 10.0f;
        final float upperBound = 30.0f;
        final float belowRange = lowerBound - 10.0f;
        final float inRange = 20.0f;
        final float aboveRange = upperBound + 10.0f;
        assertEquals(lowerBound, Limit.limit(belowRange, lowerBound, upperBound));
        assertEquals(inRange, Limit.limit(inRange, lowerBound, upperBound));
        assertEquals(upperBound, Limit.limit(aboveRange, lowerBound, upperBound));
    }

    @Test
    void testLimitBigInt() {
        final var lowerBound = BigInteger.valueOf(-100);
        final var upperBound = BigInteger.valueOf(100);
        final var belowRange = lowerBound.add(BigInteger.valueOf(-100));
        final var inRange = BigInteger.valueOf(50);
        final var aboveRange = upperBound.add(BigInteger.valueOf(100));
        assertEquals(lowerBound, Limit.limit(belowRange, lowerBound, upperBound));
        assertEquals(inRange, Limit.limit(inRange, lowerBound, upperBound));
        assertEquals(upperBound, Limit.limit(aboveRange, lowerBound, upperBound));
    }

    @Test
    void testNull() {
        assertThrowsExactly(NullPointerException.class, () -> Limit.limit(null, null, null));
    }

    @Test
    void testLimitsOrder() {
        assertThrowsExactly(IllegalArgumentException.class, () -> Limit.limit(1, 3, 0));
    }

}
