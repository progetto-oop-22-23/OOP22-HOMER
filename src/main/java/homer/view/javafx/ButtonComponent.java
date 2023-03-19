package homer.view.javafx;

import java.util.function.Function;

import homer.api.Controller;
import homer.view.StateSelector;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public final class ButtonComponent<T> extends VBox implements StateSelector<T> {

    private Button button;
    private Text text = new Text();
    private Text value = new Text();
    private Function<T, T> function;
    private T state;

    public ButtonComponent(final Controller controller, final Function<T, T> function) {
        this.function = function;
        this.getChildren().add(text);
        this.getChildren().add(button);
        this.getChildren().add(value);
        button.setOnMouseClicked(e -> {
            state = this.function.apply(state);
            this.text.setText(state.toString());
        });
    }

    @Override
    public T getState() {
        return this.state;
    }

    @Override
    public void setState(T state) {
        this.state = state;
        this.value.setText(state.toString());
    }


}
