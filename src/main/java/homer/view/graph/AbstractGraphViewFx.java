package homer.view.graph;

import java.util.Set;
import java.util.function.Function;

import homer.common.history.HistoryData;
import javafx.application.Platform;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public abstract class AbstractGraphViewFx<T> extends VBox implements GraphView<T> {

    private static final long LIMIT_ENTRIES = 30;
    private final Axis<String> timeAxis = new CategoryAxis();
    private final NumberAxis dataAxis = new NumberAxis();
    private final AreaChart<String, Number> ac = new AreaChart<>(timeAxis, dataAxis);
    private final Function<T, Number> dataTransform;

    public AbstractGraphViewFx(final String title, final Function<T, Number> dataTransform) {
        this.dataTransform = dataTransform;
        this.ac.setAnimated(false);
        this.ac.setTitle(title);

        this.getChildren().addAll(ac);
        this.timeAxis.autosize();
        this.dataAxis.autosize();
        this.ac.autosize();
        this.autosize();
    }

    @Override
    public void updateGraph(final Set<HistoryData<T>> historyData) {
        Platform.runLater(() -> {
            this.ac.getData().clear();
            final XYChart.Series<String, Number> series = new XYChart.Series<>();
            historyData.stream()
                    .sorted((dt0, dt1) -> dt0.dateTime().compareTo(dt1.dateTime()))
                    .skip(historyData.size() < LIMIT_ENTRIES ? 0 : historyData.size() - LIMIT_ENTRIES)
                    .forEach(hd -> {
                        series.getData().add(
                                new XYChart.Data<>(hd.dateTime().toString(),
                                        dataTransform.apply(hd.data())));
                    });
            this.ac.getData().add(series);
        });
    }

}
