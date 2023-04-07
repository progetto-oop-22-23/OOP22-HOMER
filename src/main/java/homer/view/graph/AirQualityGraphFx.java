package homer.view.graph;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import homer.common.history.HistoryData;
import homer.model.airquality.AirQualityState;
import javafx.application.Platform;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

/**
 * Implementation of {@link GraphView} for viewing air quality charts,
 * in JavaFX.
 */
public final class AirQualityGraphFx extends ScrollPane implements GraphView<AirQualityState> {

    private final FlowPane pane = new FlowPane();
    private final List<CustomChart> charts = List.of(
            new CustomChart("CO2 (mEq/L)", hd -> hd.data().getCO2()),
            new CustomChart("PM10 (ug/m^3)", hd -> hd.data().getPM10()),
            new CustomChart("PM2.5 (ug/m^3)", hd -> hd.data().getPM25()),
            new CustomChart("Toxic Gas (%)", hd -> hd.data().getToxicGasPercentage()));

    /**
     * Creates a new {@link AirQualityGraphFx}.
     */
    public AirQualityGraphFx() {
        this.charts.forEach(c -> {
            c.setAnimated(false);
            c.autosize();
            this.pane.getChildren().add(c);
        });
        this.pane.autosize();
        this.setContent(pane);
        // https://stackoverflow.com/questions/24136348/fit-flowpane-into-scrollpane
        // The scrollpane will let the flowpane to resize
        this.setFitToHeight(true);
        this.setFitToWidth(true);
        this.autosize();
    }

    @Override
    public void updateGraph(final Set<HistoryData<AirQualityState>> historyData) {
        Platform.runLater(() -> {
            this.charts.forEach(c -> c.clearSeries());
            historyData.stream()
                    .sorted()
                    .forEach(hd -> this.charts.forEach(c -> c.addData(hd)));
        });
    }

    private static final class CustomChart extends AreaChart<String, Number> {
        private final XYChart.Series<String, Number> series = new XYChart.Series<>();
        private final Function<HistoryData<AirQualityState>, Double> dataFunc;

        CustomChart(final String title, final Function<HistoryData<AirQualityState>, Double> dataFunc) {
            super(new CategoryAxis(), new NumberAxis());
            this.setTitle(title);
            this.setAnimated(false);
            this.legendVisibleProperty().set(false);
            this.dataFunc = dataFunc;
            this.getData().add(this.series);
        }

        public void addData(final HistoryData<AirQualityState> hd) {
            this.series.getData().add(new XYChart.Data<>(hd.dateTime().toString(), dataFunc.apply(hd)));
        }

        public void clearSeries() {
            this.series.getData().clear();
        }
    }

}
