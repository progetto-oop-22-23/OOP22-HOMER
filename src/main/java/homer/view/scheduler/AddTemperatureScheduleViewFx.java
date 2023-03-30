package homer.view.scheduler;

import java.time.LocalTime;
import java.util.function.BiConsumer;

import homer.common.bounds.Bounds;
import homer.common.temperature.Temperature;
import homer.common.temperature.TemperatureFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A box to choose the time schedule parameters and a button to add it.
 */
public final class AddTemperatureScheduleViewFx extends VBox {

    private static final double TIME_MIN = 0;
    private static final double TIME_MAX = 24;
    private static final double TEMP_MIN = 15;
    private static final double TEMP_MAX = 30;
    private final Slider timeFrom = new Slider(TIME_MIN, TIME_MAX, TIME_MIN);
    private final Slider timeTo = new Slider(TIME_MIN, TIME_MAX, TIME_MIN);
    private final Slider paramFrom = new Slider(TEMP_MIN, TEMP_MAX, TEMP_MIN);
    private final Slider paramTo = new Slider(TEMP_MIN, TEMP_MAX, TEMP_MIN);
    private final Button addBtn = new Button("Add");
    private final HBox timeBox = new HBox();
    private final HBox paramBox = new HBox();

    public AddTemperatureScheduleViewFx(final BiConsumer<Bounds<LocalTime>, Bounds<Temperature>> addAction) {

        timeFrom.setShowTickLabels(true);
        timeTo.setShowTickLabels(true);
        paramFrom.setShowTickLabels(true);
        paramTo.setShowTickLabels(true);

        // timeFrom.setShowTickMarks(true);
        // timeTo.setShowTickMarks(true);
        // paramFrom.setShowTickMarks(true);
        // paramTo.setShowTickMarks(true);

        timeFrom.setBlockIncrement(1.0d);
        timeTo.setBlockIncrement(1.0d);
        paramFrom.setBlockIncrement(0.1d);
        paramTo.setBlockIncrement(0.1d);

        timeFrom.setMajorTickUnit(1.0d);
        timeTo.setMajorTickUnit(1.0d);
        paramFrom.setMajorTickUnit(0.1d);
        paramTo.setMajorTickUnit(0.1d);

        timeFrom.setSnapToTicks(true);
        timeTo.setSnapToTicks(true);
        paramFrom.setSnapToTicks(true);
        paramTo.setSnapToTicks(true);

        this.addBtn.setOnAction(ev -> {
            final var tempMin = TemperatureFactory.fromCelsius(paramFrom.getValue());
            final var tempMax = TemperatureFactory.fromCelsius(paramTo.getValue());
            addAction.accept(
                    new Bounds<>(LocalTime.of((int) timeFrom.getValue(), 0), LocalTime.of((int) timeTo.getValue(), 0)),
                    new Bounds<>(tempMin, tempMax));
        });
        timeBox.getChildren().addAll(timeFrom, timeTo);
        paramBox.getChildren().addAll(paramFrom, paramTo);
        this.getChildren().addAll(timeBox, paramBox, addBtn);

        timeFrom.autosize();
        timeTo.autosize();
        paramFrom.autosize();
        paramTo.autosize();

        timeBox.autosize();
        paramBox.autosize();

        this.autosize();
    }

}
