package homer.view.scheduler;

import homer.common.temperature.Temperature;
import homer.model.scheduler.TimeSchedule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * A box displaying a time schedule with a button to remove it.
 */
public final class TemperatureScheduleViewFx extends HBox {

    private static final double SPACING = 10;
    private final Text timeFrom = new Text();
    private final Text timeTo = new Text();
    private final Text paramFrom = new Text();
    private final Text paramTo = new Text();
    private final Button removeBtn = new Button("Remove");

    /**
     * Creates a new {@link TemperatureScheduleViewFx}.
     * 
     * @param schedule     the time schedule.
     * @param removeAction the button event to remove this schedule.
     */
    public TemperatureScheduleViewFx(final TimeSchedule<Temperature> schedule,
            final EventHandler<ActionEvent> removeAction) {
        this.timeFrom.setText(schedule.timeBounds().getLowerBound().toString());
        this.timeTo.setText(schedule.timeBounds().getUpperBound().toString());

        this.paramFrom.setText(Double.toString(schedule.paramBounds().getLowerBound().getCelsius()));
        this.paramTo.setText(Double.toString(schedule.paramBounds().getUpperBound().getCelsius()));

        this.removeBtn.setOnAction(removeAction);

        this.setSpacing(SPACING);
        this.getChildren().addAll(this.timeFrom, this.timeTo,
                this.paramFrom, this.paramTo,
                this.removeBtn);
    }

}
