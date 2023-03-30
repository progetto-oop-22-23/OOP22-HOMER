package homer.view.scheduler;

import java.util.Map;

import homer.common.temperature.Temperature;
import homer.controller.scheduler.TimeSchedulerController;
import homer.model.scheduler.ScheduleId;
import homer.model.scheduler.TimeSchedule;
import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public final class TemperatureSchedulerViewFx extends BorderPane implements TimeSchedulerView<Temperature> {

    private TimeSchedulerController<Temperature> scheduler;
    private final VBox schedules = new VBox();

    public TemperatureSchedulerViewFx() {
        final var addSection = new AddTemperatureScheduleViewFx((tb, pb) -> this.scheduler.addSchedule(tb, pb));
        this.setTop(addSection);
        this.setCenter(new ScrollPane(schedules));

        this.autosize();
    }

    @Override
    public void updateSchedules(final Map<ScheduleId, TimeSchedule<Temperature>> schedules) {
        Platform.runLater(() -> {
            this.schedules.getChildren().clear();
            schedules.entrySet().stream().forEach(e -> {
                final var scheduleView = new TemperatureScheduleViewFx(e.getValue(),
                        ev -> this.scheduler.removeSchedule(e.getKey()));
                this.schedules.getChildren().addAll(scheduleView);
            });
        });
    }

    @Override
    public void setScheduler(final TimeSchedulerController<Temperature> scheduler) {
        this.scheduler = scheduler;
    }

}
