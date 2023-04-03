package homer.view.javafx.deviceview;

import homer.api.DeviceState;
import homer.model.airquality.AirQualityState;
import homer.view.javafx.JFXDeviceView;
import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * Simple view that displays {@link AirQualityState} parameters.
 */
public final class AirqualityView extends JFXDeviceView {
    private final Label pm25 = new Label();
    private final Label pm10 = new Label();
    private final Label toxicGasPercentage = new Label();
    private final Label co2 = new Label();

    /**
     * 
     * @param airQualityState
     */
    public AirqualityView(final AirQualityState airQualityState) {
        updateState(airQualityState);
        this.getChildren().addAll(pm25, pm10, toxicGasPercentage, co2);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof AirQualityState airQualityState) {
            updateState(airQualityState);
        }
    }

    private void updateState(final AirQualityState airQualityState) {
        Platform.runLater(() -> {
            this.pm10.setText("PM10: " + airQualityState.getPM10());
            this.pm25.setText("PM25: " + airQualityState.getPM25());
            this.co2.setText("CO2: " + airQualityState.getCO2());
            this.toxicGasPercentage.setText("TOXIC GAS: " + airQualityState.getToxicGasPercentage());
        });

    }

}
