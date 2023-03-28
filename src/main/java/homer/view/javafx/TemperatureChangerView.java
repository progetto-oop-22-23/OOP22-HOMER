package homer.view.javafx;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.DeviceView;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.model.temperaturechangers.TemperatureChangerState;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * View used to display intensity data from a {@link Heating} or an
 * {@link AirConditioning} device.
 */
public final class TemperatureChangerView extends VBox implements DeviceView {
    private final Text text = new Text("Temperature changer");
    private SliderComponent sliderComponent;

    /**
     * @param deviceId   the device's id.
     * @param state      the device's state.
     * @param controller the controller that will receive the command.
     */
    public TemperatureChangerView(final DeviceId deviceId, final TemperatureChangerState state,
            final Controller controller) {
        sliderComponent = new SliderComponent(state.getMaxIntensity().get(), state.getMinIntensity().get(),
                state.getCurrentIntensity().get(),
                () -> controller.receiveCommand(new UpdateDeviceState(deviceId,
                        new TemperatureChangerState().addCurrentIntensity(sliderComponent.getState()))));
        this.getChildren().addAll(text, sliderComponent, new DisconnectDeviceButton(controller, deviceId));
    }

    @Override
    public void setState(DeviceState state) {
        if (state instanceof TemperatureChangerState) {
            var TemperatureChangerState = (TemperatureChangerState) state;
            this.sliderComponent.setState(TemperatureChangerState.getCurrentIntensity().get());
        }
    }
}
