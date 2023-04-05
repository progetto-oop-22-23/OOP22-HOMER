package homer.view.javafx.deviceview;

import homer.api.DeviceState;
import homer.api.state.ActuatedDeviceState;
import homer.view.javafx.JFXDeviceView;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * 
 */
public final class ReadOnlyActuatedDeviceView extends JFXDeviceView {
    private final Label title = new Label("UNDEFINED");
    private final Label position = new Label();
    private final Label upperBound = new Label();
    private final Label lowerBound = new Label();

    /**
     * 
     * @param state
     */
    public ReadOnlyActuatedDeviceView(final ActuatedDeviceState state)  {
        updateState(state); 
        this.getChildren().addAll(title, position, lowerBound, upperBound);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof ActuatedDeviceState actuatedDeviceState) {
            updateState(actuatedDeviceState);
        }
    }

    private void updateState(final ActuatedDeviceState actuatedDeviceState) {
        Platform.runLater(() -> {
            actuatedDeviceState.getType().ifPresent(x -> title.setText(x));
            position.setText(Integer.toString(actuatedDeviceState.getPosition()));
            upperBound.setVisible(actuatedDeviceState.getPositionBounds().isPresent());
            lowerBound.setVisible(actuatedDeviceState.getPositionBounds().isPresent());
            actuatedDeviceState.getPositionBounds().ifPresent(s -> {
                lowerBound.setText("Lower bound: " + s.getLowerBound());
                upperBound.setText("Upper bound: " + s.getUpperBound());
            });
        }); 
    }
}
