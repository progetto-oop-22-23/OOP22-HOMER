package homer.view.javafx;

import homer.view.StateSelector;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Button component.
 * @param <T> The state parameter that is going to be displayed.
 */
public final class ButtonComponent<T> extends VBox implements StateSelector<T> {

    private Button button;
    private T state;
    private final Label stateLabel = new Label();

    /**
     * 
     * @param onclick What should happen when the button is clicked.
     * @param title The button's title. Effectively final.
     * @param state The initial state.
     */
    public ButtonComponent(final EventHandler<? super MouseEvent> onclick, final String title, final T state) {
        this.setState(state);
        this.stateLabel.setText(state.toString());
        this.getChildren().addAll(button, new Label(title), stateLabel);
        button.setOnMouseClicked(onclick);
    }

    @Override
    public T getState() {
        return this.state;
    }

    @Override
    public void setState(final T state) {
        this.state = state;
        this.stateLabel.setText(state.toString());
    }


}
