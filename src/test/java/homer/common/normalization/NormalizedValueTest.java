package homer.common.normalization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import homer.common.bounds.Bounds;

final class NormalizedValueTest {

    private static final double NORMALIZED_BOTTOM = 0.0;
    private static final double NORMALIZED_TOP = 100.0;
    private static final double RESCALED_BOTTOM = 0.0;
    private static final double RESCALED_TOP = 5000.0;
    private static final Bounds<Double> NORMALIZED_SCALE = new Bounds<>(NORMALIZED_BOTTOM, NORMALIZED_TOP);
    private static final Bounds<Double> NEW_SCALE = new Bounds<>(RESCALED_BOTTOM, RESCALED_TOP);

    @Test
    void testRescale() {
        final var normalizedHalf = (NORMALIZED_SCALE.getLowerBound() + NORMALIZED_SCALE.getUpperBound()) / 2.0;
        final var rescaledHalf = (NEW_SCALE.getLowerBound() + NEW_SCALE.getUpperBound()) / 2.0;

        checkEquals(NORMALIZED_BOTTOM, RESCALED_BOTTOM);
        checkEquals(NORMALIZED_TOP, RESCALED_TOP);
        checkEquals(normalizedHalf, rescaledHalf);
    }

    private void checkEquals(final double normalized, final double rescaled) {
        assertEquals(rescaled, new NormalizedValueImpl(normalized, NORMALIZED_SCALE).rescale(NEW_SCALE));
        assertEquals(rescaled, NormalizedValueImpl.rescaleMinMax(normalized, NORMALIZED_SCALE, NEW_SCALE));
    }
}
