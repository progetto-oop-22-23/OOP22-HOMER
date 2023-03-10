package homer.common.limit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

// CHECKSTYLE: MagicNumber OFF
// Rule disabled in test class.

final class TestLimit {

    @Test
    void testLimit() {
        final var lowerLimit = 10;
        final var upperLimit = 30;
        assertEquals(lowerLimit, Limit.limit(0, lowerLimit, upperLimit));
        assertEquals(20, Limit.limit(20, lowerLimit, upperLimit));
        assertEquals(upperLimit, Limit.limit(40, lowerLimit, upperLimit));
    }

}
