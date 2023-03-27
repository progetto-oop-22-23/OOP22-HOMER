package homer.view.javafx;

import homer.view.StateSelector;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Basic slider that displays its value.
 */
public final class SliderComponent extends VBox implements StateSelector<Double> {

    private Slider slider;
    private Text value = new Text();

    /**
     * 
     * @param max
     * @param min
     * @param value
     * @param onDragDone the event that gets triggered on drag done.
     */
    public SliderComponent(final double max, final double min, final double value,
            final EventHandler<? super DragEvent> onDragDone) {
        this.slider = new Slider(min, max, value);
        this.slider.setOnDragDone(onDragDone);
        this.getChildren().add(slider);
        this.getChildren().add(this.value);
    }

    @Override
    public Double getState() {
        return this.slider.getValue();
    }

    @Override
    public void setState(final Double state) {
        this.slider.setValue(state);
    }

}
