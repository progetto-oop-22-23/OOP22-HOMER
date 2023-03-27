package homer.common.normalization;

import java.util.Objects;

import homer.common.bounds.Bounds;
import homer.common.limit.Limit;

/**
 * Implementation of {@link NormalizedValue}.
 */
public final class NormalizedValueImpl implements NormalizedValue {

    private final double value;
    private final Bounds<Double> scale;

    /**
     * Constructs a {@code NormalizedValue}.
     * 
     * @param value the value.
     * @param scale the scale of the {@code value}.
     */
    public NormalizedValueImpl(final double value, final Bounds<Double> scale) {
        this.scale = Objects.requireNonNull(scale);
        this.value = Limit.clamp(value, scale.getLowerBound(), scale.getUpperBound());
    }

    @Override
    public double rescale(final Bounds<Double> newScale) {
        return rescaleMinMax(this.value, this.scale, newScale);
    }

    /**
     * Rescales a {@code value} in scale {@code oldScale} to {@code newScale}.
     * 
     * @param value    the value in the {@code oldScale}.
     * @param oldScale the {@code value} scale.
     * @param newScale the new scale.
     * @return the {@code value} rescaled to {@code newScale}.
     */
    public static double rescaleMinMax(final double value, final Bounds<Double> oldScale,
            final Bounds<Double> newScale) {
        // https://en.wikipedia.org/wiki/Feature_scaling#Rescaling_(min-max_normalization)
        // rescale a range between an arbitrary set of values [a, b]
        final double a = newScale.getLowerBound();
        final double b = newScale.getUpperBound();
        final double minX = oldScale.getLowerBound();
        final double maxX = oldScale.getUpperBound();
        final double x = value;
        return a + ((x - minX) * (b - a)) / (maxX - minX);
    }

}
