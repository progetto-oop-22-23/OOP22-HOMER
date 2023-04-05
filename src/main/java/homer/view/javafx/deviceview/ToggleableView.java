package homer.view.javafx.deviceview;

import java.util.function.Consumer;

import homer.view.StateSelector;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * Generic view for toggleable devices.
 */
public final class ToggleableView extends VBox implements StateSelector<Boolean> {

    private Boolean state = false;
    private final String offRepresentation;
    private final String onRepresentation;
    private final Button button = new Button();

    /**
     * 
     * @param onRepresentation What should be displayed when the state is true.
     * @param offRepresentation What should be displayed when the state is false.
     * @param consumer The {@link Consumer} that will get the parameter.
     */
    public ToggleableView(final String onRepresentation, final String offRepresentation,
            final Consumer<Boolean> consumer) {
        this.onRepresentation = onRepresentation;
        this.offRepresentation = offRepresentation;
        updateState(state);
        this.getChildren().add(button);
        button.setOnMouseClicked(e -> {
            consumer.accept(!state);
            updateState(!state);
        });
    }

    private void updateState(final Boolean state) {
        this.state = state;
        Platform.runLater(() -> button.setText(this.state ? onRepresentation : offRepresentation));
    }

    @Override
    public Boolean getState() {
        return this.state;
    }

    @Override
    public void setState(final Boolean state) {
        this.state = state;
    }
}
