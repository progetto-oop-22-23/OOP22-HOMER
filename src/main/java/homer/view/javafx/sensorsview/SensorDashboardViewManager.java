package homer.view.javafx.sensorsview;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.airquality.AirqualitySensor;
import homer.model.environment.HomeEnvironment;
import homer.model.thermometer.SimpleThermometer;

/**
 * Generates and updates the sensors dashboard.
 */
public final class SensorDashboardViewManager {
    @FXML
    // Reference of consumptionLabel for the FXML loader.
    private Label consumptionLabel;

    @FXML
    // Reference of powerLabel for the FXML loader.
    private Label powerLabel;

    @FXML
    // Reference of temperatureLabel for the FXML loader.
    private Label temperatureLabel;

    @FXML
    // Reference of PM10Label for the FXML loader.
    private Label pm10Label;

    @FXML
    // Reference of PM25Label for the FXML loader.
    private Label pm25Label;

    @FXML
    // Reference of CO2Label for the FXML loader.
    private Label co2Label;

    @FXML
    // Reference of ToxicGasLabel for the FXML loader.
    private Label toxicGasLabel;
    // Automatically set by FXML Loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    private ElectricalMeterImpl meter;

    private AirqualitySensor airQuality;

    private SimpleThermometer thermometer;

    private HomeEnvironment environment;

    /**
     * 
     * 
     * public SensorDashboardViewManager() {
     * 
     * }
     */
    /**
     * Sets sensors
     * {@link homer.view.javafx.sensorsview.SensorDashboardViewManager}.
     * 
     * @param meter       The {@link homer.controller.impl.electricalmeter}.
     * @param environment The {@link homer.model.environment.HomeEnvironment}.
     */
    public void setSensors(final ElectricalMeterViewManager meterView, final HomeEnvironment environment) {
        this.meter = meterView.getMeter();
        this.environment = new HomeEnvironment(environment);
        this.airQuality = new AirqualitySensor(this.environment);
        this.thermometer = new SimpleThermometer(this.environment);
    }

    /**
     * Set {@code meter} to a new
     * {@link homer.controller.impl.electricalmeter.ElectricalMeterImpl}.
     * 
     * @param meter the new {@code ElectricalMeterImpl}.
     */
    public void setMeter(final ElectricalMeterImpl meter) {
        Objects.requireNonNull(meter);
        this.meter = meter;
    }

    /**
     * Sets {@code environment} to a new
     * {@link homer.model.environment.HomeEnvironment}.
     * 
     * @param environment the new {@code HomeEnvironment}.
     */
    public void setEnvironment(final HomeEnvironment environment) {
        Objects.requireNonNull(environment);
        this.environment = new HomeEnvironment(environment);
    }

    /**
     * Sets {@code airQuality} to a new
     * {@link homer.model.airquality.AirqualitySensor}.
     * 
     * @param environment the {@link homer.model.environment.HomeEnvironment} to
     *                    create a new
     *                    {@code AirqualitySensor}.
     */
    public void setAirQuality(final HomeEnvironment environment) {
        Objects.requireNonNull(environment);
        this.airQuality = new AirqualitySensor(environment);
    }

    /**
     * Sets {@code thermometer} to a new
     * {@link homer.model.thermometer.SimpleThermometer}.
     * 
     * @param environment the {@link homer.model.environment.HomeEnvironment} to
     *                    create a new
     *                    {@code SimpleThermometer}.
     */
    public void setThermometer(final HomeEnvironment environment) {
        Objects.requireNonNull(environment);
        this.thermometer = new SimpleThermometer(environment);
    }

    /**
     * Updates the view.
     */
    public void setLabels() {
        final double consumption = this.meter.getGlobalConsumption();
        final double power = this.meter.getAveragePower();
        final double temperature = this.thermometer.getState().getTemperature().getCelsius();
        consumptionLabel.setText(String.format("Global Consumption: %.2f Wh", consumption));
        powerLabel.setText(String.format("Instant power: %.2f W", power));
        temperatureLabel.setText(String.format("Temperature: %.1f °C", temperature));
        pm10Label.setText(String.format("PM10 (ppM): %.1f", airQuality.getState().getPM10()));
        pm25Label.setText(String.format("PM2.5 (ppM): %.1f", airQuality.getState().getPM25()));
        co2Label.setText(String.format("CO2 (%): %.1f", airQuality.getState().getCO2()));
        toxicGasLabel.setText(String.format("Toxic gas (%): %.1f", airQuality.getState().getToxicGasPercentage()));
    }
}
