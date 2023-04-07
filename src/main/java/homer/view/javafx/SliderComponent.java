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
public class SliderComponent extends VBox implements StateSelector<Double> {

    private final Slider slider;

    private final Label label;

    /**
     * Supports continuous values.
     * 
     * @param max        The maximum value the slider can assume.
     * @param min        The minimum value the slider can assume.
     * @param current    The current value the slider assumes.
     * @param onDragDone the event that gets triggered on drag done.
     */
    public SliderComponent(final double max, final double min, final double current,
            final Consumer<Double> onDragDone) {
        label = new Label(Double.toString(current));
        this.slider = new Slider(min, max, current);
        this.slider.setShowTickLabels(true);
        this.slider.setShowTickMarks(true);
        super.getChildren().addAll(this.slider, this.label);
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


    private void updateValue(final Double value) {
        this.slider.setValue(value);
        this.label.setText(Double.toString(value));
    }

    @Override
    public final Double getState() {
        return this.slider.getValue();
    }

    @Override
    public final void setState(final Double state) {
        updateValue(state);
    }

    /**
     * Returns the actual slider.
     * @return the actual slider.
     */
    protected final Slider getSlider() {
        return slider;
    }
}
