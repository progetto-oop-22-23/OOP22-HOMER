package homer.view.javafx.deviceview;

import java.util.function.Consumer;

import homer.view.StateSelector;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public final class ToggleableView extends VBox implements StateSelector<Boolean> {

    private Boolean state = false;
    private final String isOff;
    private final String isOn;
    private final Button button = new Button();

    public ToggleableView(final String isOn, final String isOff, final Consumer<Boolean> consumer) {
        this.isOn = isOn;
        this.isOff = isOff;
        this.getChildren().add(button);
        button.setOnMouseClicked(e -> {
            consumer.accept(!state);
            updateState(!state);
        });
    }

    private void updateState(final Boolean state) {
        this.state = state;
        Platform.runLater(() -> button.setText(this.state ? isOn : isOff));
    }

    @Override
    public Boolean getState() {
        return this.state;
    }

    @Override
    public void setState(Boolean state) {

    }
}
