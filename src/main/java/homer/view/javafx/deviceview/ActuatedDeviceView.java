package homer.view.javafx.deviceview;

import java.util.Optional;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.state.ActuatedDeviceState;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.model.actuator.AbstractActuatedDevice;
import homer.view.javafx.DiscreteSliderComponent;
import homer.view.javafx.JFXDeviceView;
import homer.view.javafx.SliderComponent;
import javafx.scene.control.Label;

/**
 * View that displays informations relevant to
 * {@link homer.model.actuator.ActuatedDevice}.
 */
public final class ActuatedDeviceView extends JFXDeviceView {
    private final SliderComponent sliderComponent;

    /**
     * 
     * @param deviceId the device's id.
     * @param state the current state we want to set the slider to.
     * @param controller the controller that will be updated.
     */
    public ActuatedDeviceView(final DeviceId deviceId, final ActuatedDeviceState state,
            final Controller controller) {
        final var bounds = state.getPositionBounds().get(); // we need bounds on constructor
        sliderComponent = new DiscreteSliderComponent(bounds.getUpperBound().intValue(), bounds.getLowerBound().intValue(),
                state.getPosition(),
                s -> {
                    controller.receiveCommand(
                            new UpdateDeviceState(deviceId, new ActuatedDeviceState(s.intValue(), Optional.empty())));
                });
        this.getChildren().addAll(new Label(state.getType().get()), sliderComponent);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof AbstractActuatedDevice temperatureChangerState) {
            this.sliderComponent.setState((double) temperatureChangerState.getState().getPosition());
        }
    }
}
