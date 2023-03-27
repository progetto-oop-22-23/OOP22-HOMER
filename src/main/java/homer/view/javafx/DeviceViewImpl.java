package homer.view.javafx;

import homer.api.DeviceId;
import homer.controller.Controller;
import homer.view.StateSelector;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Generic view that allows to get state and set the modifiable component of the device.
 */
public final class DeviceViewImpl<T> extends VBox implements StateSelector<T> {
    private final Text text;
    private final StateSelector<T> stateSelector;
    private final Button disconnect; 

    private static final Border DEFAULT_BORDER = new Border(
        new BorderStroke(null, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)
    );

    /**
     * @param stateSelector The view component that actually allows us to select state
     * @param controller The controller that will receive updates.
     * @param deviceId The device's id.
     */
    public DeviceViewImpl(final StateSelector<T> stateSelector, final Controller controller, final DeviceId deviceId) {
        this.stateSelector = stateSelector;
        this.text = new Text();
        this.text.setText("Something");
        this.disconnect = new DisconnectDeviceButton(controller, deviceId);
        this.getChildren().addAll((Node) stateSelector, disconnect);
        this.setBorder(DEFAULT_BORDER);
    }

    @Override
    public T getState() {
        return this.stateSelector.getState();
    }

    @Override
    public void setState(final T state) {
        this.stateSelector.setState(state);
    }

}
