package homer.view.javafx;

import homer.api.DeviceIdImpl;
import homer.controller.Controller;
import homer.view.StateSelector;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Basic slider that displays its value.
 */
public final class SliderComponent extends VBox implements StateSelector<Double> {

    private Slider slider; 
    private Text text = new Text();
    private Text value = new Text();

    /**
     * 
     * @param controller
     * @param max
     * @param min
     * @param value
     */
    public SliderComponent(final Controller controller, final double max, final double min, final double value) {
        this.slider = new Slider(min, max, value);
        this.getChildren().add(text);
        this.getChildren().add(slider);
        this.getChildren().add(this.value);
    }

    @Override
    public Double getState() {
        return this.slider.getValue();
    }

    @Override
    public void setState(Double state) {
        this.slider.setValue(state);
    }
    
}
