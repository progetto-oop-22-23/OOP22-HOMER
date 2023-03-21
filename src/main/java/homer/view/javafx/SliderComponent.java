package homer.view.javafx;

import homer.api.DeviceIdImpl;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.view.StateSelector;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SliderComponent extends VBox implements StateSelector<Double> {

    private Slider slider; 
    private Text text = new Text();
    private Text value = new Text();

    public SliderComponent(final Controller controller, final double max, final double min, final double value) {
        this.slider = new Slider(min, max, value);
        this.getChildren().add(text);
        this.getChildren().add(slider);
        this.getChildren().add(this.value);
        slider.setOnDragDone(e -> controller.receiveCommand(new UpdateDeviceState(new DeviceIdImpl(), this.slider.getValue())));
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
