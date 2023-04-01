package homer.view.javafx.sensorsview;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
//import homer.model.airquality.AirQualityImpl;
import homer.model.environment.HomeEnvironment;
import homer.model.thermometer.SimpleThermometer;

public class SensorDashboardViewManager {
    @FXML
    // Reference of consumptionLabel for the FXML loader.
    private Label consumptionLabel;

    @FXML
    // Reference of powerLabel for the FXML loader.
    private Label powerLabel;

    @FXML
    // Reference of temperatureLabel for the FXML loader.
    private Label temperatureLabel;
    // Automatically set by FXML Loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    private ElectricalMeterImpl meter;

    // private AirQualityImpl airQuality;

    private SimpleThermometer thermometer;

    /**
     * Constructor for
     * {@link homer.view.javafx.sensorsview.SensorDashboardViewManager}.
     * 
     * @param meter       The {@link homer.controller.impl.electricalmeter}.
     * @param environment The {@link homer.model.environment.HomeEnvironment}.
     */
    public SensorDashboardViewManager(final ElectricalMeterImpl meter, final HomeEnvironment environment) {
        this.meter = new ElectricalMeterImpl(meter.getOutlets());
        // this.airQuality = new AirQualityImpl(environment);
        this.thermometer = new SimpleThermometer(environment);
    }

    public void setMeter(final ElectricalMeterImpl meter) {
        Objects.requireNonNull(meter);
        this.meter = meter;
    }

    /*
     * public void setAirQuality(final AirQualityImpl airQuality) {
     * Objects.requireNonNull(airQuality);
     * this.airQuality = airQuality;
     * }
     */
    public void setThermometer(final SimpleThermometer thermometer) {
        Objects.requireNonNull(thermometer);
        this.thermometer = thermometer;
    }

    public void setLabels() {
        final double consumption = this.meter.getGlobalConsumption();
        final double power = this.meter.getAveragePower();
        final double temperature = this.thermometer.getState().getTemperature().getCelsius();
        consumptionLabel.setText(String.format("Global Consumption: %.2f Wh", consumption));
        powerLabel.setText(String.format("Instant power: %.2f W", power));
        temperatureLabel.setText(String.format("Temperature: %.1f °C", temperature));
    }
}
