package homer.view.javafx;

import java.util.function.Consumer;

/**
 * Slider component that only supports discrete updates.
 */
public class DiscreteSliderComponent extends SliderComponent {
    /**
     * 
     * @param max        maximum slider value.
     * @param min        minimum slider value.
     * @param current    current slider value.
     * @param onDragDone the event that gets triggered on drag done.
     */
    public DiscreteSliderComponent(final int max, final int min, final int current,
            final Consumer<Double> onDragDone) {
        super((double) max, (double) min, (double) current, onDragDone);
        this.getSlider().setBlockIncrement(1);
        this.getSlider().setMajorTickUnit(1);
        this.getSlider().setMinorTickCount(0);
        this.getSlider().setSnapToTicks(true);
        this.getSlider().setShowTickMarks(false);
    }

}
