package homer.view.javafx;

import homer.controller.Controller;
import homer.view.StateSelector;
import javafx.scene.Node;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public final class DeviceViewImpl<T> extends VBox implements StateSelector<T> {
    private final Text text;
    private StateSelector<T> stateSelector;

    public DeviceViewImpl(final StateSelector<T> stateSelector, final Controller controller) {
        this.stateSelector = stateSelector;
        this.text = new Text();
        this.text.setText("Something");
        this.getChildren().add((Node) stateSelector);
        this.setBorder(new Border(new BorderStroke(null, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    public T getState() {
        return this.stateSelector.getState();
    }

    @Override
    public void setState(T state) {
        this.stateSelector.setState(state);
    }

}
