package homer.common.normalization;

import homer.common.bounds.Bounds;

/**
 * Represents a normalized value.
 */
public interface NormalizedValue {

    /**
     * Rescales the value to a new scale.
     * 
     * @param newScale the new scale.
     * @return the value rescaled to {@code newScale}.
     */
    double rescale(Bounds<Double> newScale);

}
