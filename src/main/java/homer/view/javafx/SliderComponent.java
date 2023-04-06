package homer.view.javafx;

import java.util.function.Consumer;

import homer.view.StateSelector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

/**
 * Basic slider that displays its value.
 */
public final class SliderComponent extends VBox implements StateSelector<Double> {

    private final Slider slider;
    private final Label label;

    /**
     * Supports continuous values.
     * @param max The maximum value the slider can assume.
     * @param min The minimum value the slider can assume.
     * @param current The current value the slider assumes.
     * @param onDragDone the event that gets triggered on drag done.
     */
    public SliderComponent(final double max, final double min, final double current,
            final Consumer<Double> onDragDone) {
        label = new Label(Double.toString(current));
        this.slider = new Slider(min, max, current);
        this.slider.setShowTickLabels(true);
        this.slider.setShowTickMarks(true);
        this.getChildren().addAll(this.slider, this.label);
        this.slider.valueProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(final ObservableValue<? extends Number> observable,
                            final Number oldValue, final Number newValue) {
                        updateValue(newValue.doubleValue());
                    }
                });
        this.slider.valueChangingProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                onDragDone.accept(getState());
            }
        });
    }

    /**
     * Supports discrete values only.
     * @param max maximum slider value.
     * @param min minimum slider value.
     * @param current current slider value.
     * @param onDragDone the event that gets triggered on drag done.
     */
    public SliderComponent(final int max, final int min, final int current,
            final Consumer<Double> onDragDone) {
                this((double) max, (double) min, (double) current, onDragDone);
                this.slider.setBlockIncrement(1);
                this.slider.setMajorTickUnit(1);
                this.slider.setMinorTickCount(0);
                this.slider.setSnapToTicks(true);
                this.slider.setShowTickMarks(false);
    }

    private void updateValue(final Double value) {
        this.slider.setValue(value);
        this.label.setText(Double.toString(value));
    }

    @Override
    public Double getState() {
        return this.slider.getValue();
    }

    @Override
    public void setState(final Double state) {
        updateValue(state);
    }

}
