package homer.view.javafx;

import homer.api.Device;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DeviceView<S> extends VBox {
    private Text text; 
    private S state;

    public DeviceView(S state) {
        text = new Text(state.toString());
        this.getChildren().add(text);
        this.state = state;
    }

}
