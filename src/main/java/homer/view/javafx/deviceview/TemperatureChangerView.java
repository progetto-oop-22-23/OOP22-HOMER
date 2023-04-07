package homer.view.javafx.deviceview;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.javafx.JFXDeviceView;
import homer.view.javafx.SliderComponent;
import javafx.scene.control.Label;

/**
 * View used to display intensity data from a
 * {@link homer.model.temperaturechangers.Heating} or an
 * {@link homer.model.temperaturechangers.AirConditioning} device.
 */
public final class TemperatureChangerView extends JFXDeviceView {
    private final SliderComponent sliderComponent;

    /**
     * @param deviceId   the device's id.
     * @param state      the device's state.
     * @param controller the controller that will receive the command.
     */
    public TemperatureChangerView(final DeviceId deviceId, final TemperatureChangerState state,
            final Controller controller) {
        sliderComponent = new SliderComponent(state.getMaxIntensity().get(), state.getMinIntensity().get(),
                state.getCurrentIntensity().get(),
                s -> controller.receiveCommand(new UpdateDeviceState(deviceId,
                        new TemperatureChangerState().addCurrentIntensity(s))));
        final String title = state.getType()
                .map(x -> TemperatureChangerState.AIRCONDITIONING.equals(x) ? "Air Conditioning" : "Heating")
                .orElse("Undefined Temperature Changer Device");
        this.getChildren().addAll(new Label(title), sliderComponent);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof TemperatureChangerState temperatureChangerState) {
            this.sliderComponent.setState(temperatureChangerState.getCurrentIntensity().get());
        }
    }

}
