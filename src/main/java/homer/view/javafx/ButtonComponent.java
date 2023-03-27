package homer.view.javafx;

import homer.controller.Controller;
import homer.view.StateSelector;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Button component.
 */
public final class ButtonComponent<T> extends VBox implements StateSelector<T> {

    private Button button;
    private Text text = new Text();
    private Text value = new Text();
    private T state;

    public ButtonComponent(final Controller controller, final EventHandler<? super MouseEvent> e) {
        this.getChildren().add(text);
        this.getChildren().addAll(button, value);
        button.setOnMouseClicked(e);
    }

    @Override
    public T getState() {
        return this.state;
    }

    @Override
    public void setState(final T state) {
        this.state = state;
        this.value.setText(state.toString());
    }

}
