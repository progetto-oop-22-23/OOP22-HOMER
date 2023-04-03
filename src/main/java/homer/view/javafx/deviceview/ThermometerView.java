package homer.view.javafx.deviceview;

import homer.api.DeviceState;
import homer.api.state.ThermometerState;
import homer.common.temperature.Temperature;
import homer.view.javafx.JFXDeviceView;
import javafx.scene.control.Label;

/**
 * Read only view for thermometers.
 * 
 */
public final class ThermometerView extends JFXDeviceView {
    // TODO the temperature's unit measure could be set univocally in the view, but for now this should be fine
    private final Label celsius = new Label();
    private final Label farenheit = new Label();
    private final Label kelvin = new Label();

    /**
     * 
     * @param thermometerState
     * 
     */
    public ThermometerView(final ThermometerState thermometerState) {
        updateState(thermometerState.getTemperature());
        this.getChildren().addAll(celsius, farenheit, kelvin);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof ThermometerState thermometerState) {
            updateState(thermometerState.getTemperature());
        }
    }

    private void updateState(final Temperature temperature) {
        celsius.setText("CELSIUS:" + temperature.getCelsius());
        kelvin.setText("KELVIN:" + temperature.getKelvin());
        farenheit.setText("farenheit:" + temperature.getFarenheit());
    }

}