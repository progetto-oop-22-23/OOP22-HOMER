package homer.view.scheduler;

import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.controlsfx.control.RangeSlider;

import homer.common.bounds.Bounds;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A box to choose the time schedule parameters and a button to add it.
 */
public final class AddTemperatureScheduleViewFx extends VBox {

    private static final double TIME_MIN = 0;
    private static final double TIME_MAX = 24;
    private static final double TEMP_MIN = 15;
    private static final double TEMP_MAX = 30;
    private static final double MAJOR_TICK_UNIT = 1.0d;
    private static final int MINOR_TICK_COUNT = 0;
    private final Text timeText = new Text("Time");
    private final Text tempText = new Text("Temperature (C)");
    private final RangeSlider timeRange = new RangeSlider(TIME_MIN, TIME_MAX, TIME_MIN, TIME_MAX);
    private final RangeSlider tempRange = new RangeSlider(TEMP_MIN, TEMP_MAX, TEMP_MIN, TEMP_MAX);
    private final Button addBtn = new Button("Add");

    /**
     * Creates a new {@link AddTemperatureScheduleViewFx}.
     * 
     * @param addAction The action to perform when the add button is pressed.
     */
    public AddTemperatureScheduleViewFx(final BiConsumer<Bounds<Integer>, Bounds<Temperature>> addAction) {
        Stream.of(timeRange, tempRange).forEach(rs -> {
            rs.setShowTickLabels(true);
            rs.setShowTickMarks(true);
            rs.setBlockIncrement(MAJOR_TICK_UNIT);
            rs.setMajorTickUnit(MAJOR_TICK_UNIT);
            rs.setMinorTickCount(MINOR_TICK_COUNT);
            rs.setSnapToTicks(true);
        });

        this.addBtn.setOnAction(ev -> {
            final var tempMin = TemperatureFactory.fromCelsius(tempRange.getLowValue());
            final var tempMax = TemperatureFactory.fromCelsius(tempRange.getHighValue());
            addAction.accept(
                    new Bounds<>((int) timeRange.getLowValue(), (int) timeRange.getHighValue()),
                    new Bounds<>(tempMin, tempMax));
        });
        this.getChildren().addAll(timeText, timeRange, tempText, tempRange, addBtn);

        timeRange.autosize();
        tempRange.autosize();

        this.autosize();
    }

}
