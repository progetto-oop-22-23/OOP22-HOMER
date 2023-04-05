package homer.view.javafx.sensorsview;

import java.net.URL;
import java.util.ResourceBundle;

import homer.model.airquality.AirqualitySensor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * View manager for {@link homer.model.airquality.AirqualitySensor}.
 */
public final class AirQualityViewManager {

    private AirqualitySensor airQuality;

    @FXML
    private Label co2Label;

    @FXML
    private Label pm10Label;

    @FXML
    private Label pm25Label;

    @FXML
    private Label toxicGasLabel;

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    /**
     * Empty constructor for AirQualityViewManager.
     */
    public AirQualityViewManager() {

    }

    /**
     * Sets {@code airQuality}.
     * @param airQuality    The new {@code airQuality}.
     */
    public void setAirQuality(AirqualitySensor airQuality) {
        this.airQuality = airQuality;
    }

    /**
     * Updates view.
     */
    public void setLabels() {
        co2Label.setText("CO2 (%): " + this.airQuality.getState().getCO2());
        pm10Label.setText("PM10 (ppM): " + this.airQuality.getState().getPM10());
        pm25Label.setText("PM2.5 (ppM): " + this.airQuality.getState().getPM25());
        toxicGasLabel.setText("Toxic Gas (%): " + this.airQuality.getState().getToxicGasPercentage());
    }
}
