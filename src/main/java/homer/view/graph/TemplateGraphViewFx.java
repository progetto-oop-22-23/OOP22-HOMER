package homer.view.graph;

import java.util.Set;
import java.util.function.Function;

import homer.common.history.HistoryData;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

/**
 * Template implementation of {@link GraphView} in JavaFX.
 * {@code updateGraph} becomes a template method using the abstract method
 * {@code dataTransform} to display the data.
 * 
 * @param <T> the type of data logged to be displayed.
 */
public class TemplateGraphViewFx<T> extends VBox implements GraphView<T> {

    private final Axis<String> timeAxis = new CategoryAxis();
    private final NumberAxis dataAxis = new NumberAxis();
    private final AreaChart<String, Number> ac = new AreaChart<>(timeAxis, dataAxis);
    private final Function<T, Number> dataTransform;

    /**
     * Constructor for {@link TemplateGraphViewFx}.
     * 
     * @param title         the title of the graph to display.
     * @param dataTransform function that takes the data and converts it to a
     *                      {@link Number}.
     */
    public TemplateGraphViewFx(final String title, final Function<T, Number> dataTransform) {
        this.dataTransform = dataTransform;
        this.ac.setAnimated(false);
        this.ac.setTitle(title);
        this.ac.legendVisibleProperty().set(false);

        this.getChildren().addAll(ac);
        this.timeAxis.autosize();
        this.dataAxis.autosize();
        this.ac.autosize();
        this.autosize();
    }

    @Override
    public final void updateGraph(final Set<HistoryData<T>> historyData) {
        Platform.runLater(() -> {
            this.ac.getData().clear();
            final XYChart.Series<String, Number> series = new XYChart.Series<>();
            historyData.stream()
                    .sorted()
                    .forEach(hd -> series.getData().add(createData(hd)));
            this.ac.getData().add(series);
        });
    }

    // Made final to be able to call it in the constructor safely.
    @Override
    public final ObservableList<Node> getChildren() {
        return super.getChildren();
    }

    private XYChart.Data<String, Number> createData(final HistoryData<T> hd) {
        return new XYChart.Data<>(hd.dateTime().toString(), this.dataTransform.apply(hd.data()));
    }

}
