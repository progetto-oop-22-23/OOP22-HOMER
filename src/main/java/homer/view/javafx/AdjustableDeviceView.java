package homer.view.javafx;

import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Value always needs to be mapped as a double.
 */
public class AdjustableDeviceView extends VBox {
    private Slider slider;
    private Text text;

    public AdjustableDeviceView(String title, double min, double max, double value) {
        this.slider = new Slider(min, max, value);
        this.slider.showTickLabelsProperty();
        this.text = new Text(title);
        this.getChildren().add(this.text);
        this.getChildren().add(this.slider);
        this.slider.setOnDragDetected(e -> System.out.println(this.slider.getValue()));
    }

}
