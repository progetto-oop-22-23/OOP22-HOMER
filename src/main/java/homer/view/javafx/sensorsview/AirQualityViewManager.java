package homer.view.javafx.sensorsview;

import java.net.URL;
import java.util.ResourceBundle;

import homer.controller.Controller;
import homer.model.airquality.AirQualityState;
import homer.model.airquality.AirqualitySensor;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * View manager for {@link homer.model.airquality.AirqualitySensor}.
 */
public final class AirQualityViewManager {

    private Controller controller;

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
     * Sets {@code controller}.
     * 
     * @param controller The new {@code controller}.
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * Updates view.
     */
    public void updateView() {
        final AirQualityState state = this.controller.getDeviceManager().getDevices().values().stream()
                .filter(dev -> dev instanceof AirqualitySensor)
                .map(dev -> ((AirqualitySensor) dev).getState())
                .findFirst()
                .orElse(null);

        if (state != null) {
            co2Label.setText("CO2 (%): " + state.getCO2());
            pm10Label.setText("PM10 (ppM): " + state.getPM10());
            pm25Label.setText("PM2.5 (ppM): " + state.getPM25());
            toxicGasLabel.setText("Toxic Gas (%): " + state.getToxicGasPercentage());
        }
    }
}
