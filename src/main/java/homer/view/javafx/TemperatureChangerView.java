package homer.view.javafx;

import homer.api.DeviceId;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.model.temperaturechangers.TemperatureChangerState;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TemperatureChangerView extends VBox {
    private final Text text = new Text();
    private SliderComponent sliderComponent;

    public TemperatureChangerView(DeviceId deviceId, TemperatureChangerState state, Controller controller) {
        sliderComponent = new SliderComponent(state.getMaxIntensity().get(), state.getMinIntensity().get(), state.getCurrentIntensity().get(), 
            e -> {
                System.out.println(sliderComponent.getState());
                controller.receiveCommand(new UpdateDeviceState(deviceId, new TemperatureChangerState().addCurrentIntensity(sliderComponent.getState())));
            });
        this.getChildren().add(sliderComponent);
    }
}
