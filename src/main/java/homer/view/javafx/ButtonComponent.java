package homer.view.javafx;

import homer.view.StateSelector;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Button component.
 * @param <T> not really sure about keeping this
 */
public final class ButtonComponent<T> extends VBox implements StateSelector<T> {

    private Button button;
    private T state;
    private final Text stateText;

    /**
     * 
     * @param onclick 
     * @param title The button's title. Effectively final.
     * @param state The initial state.
     */
    public ButtonComponent(final EventHandler<? super MouseEvent> onclick, final String title, final T state) {
        stateText = new Text();
        this.setState(state);
        this.getChildren().addAll(button, new Text(title), stateText);
        button.setOnMouseClicked(onclick);
    }

    @Override
    public T getState() {
        return this.state;
    }

    @Override
    public void setState(final T state) {
        this.state = state;
        this.stateText.setText(state.toString());
    }


}
