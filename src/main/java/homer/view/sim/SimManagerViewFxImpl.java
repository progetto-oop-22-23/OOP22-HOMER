package homer.view.sim;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import homer.core.SimManagerViewObserver;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Implementation of {@link SimManagerView} using JavaFX.
 */
public final class SimManagerViewFxImpl extends VBox implements SimManagerView {

    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";
    private static final String ONE_SEC = "1s";
    private static final String ONE_MIN = "1m";
    private static final String ONE_HOUR = "1h";
    // private static final double HEIGHT_SCALE = 0.2;
    // private static final double WIDTH_SCALE = 0.2;
    private final HBox upperSection = new HBox();
    private final HBox lowerSection = new HBox();
    private final Text datetime = new Text();
    private SimManagerViewObserver simManager;

    /**
     * Creates a new {@link SimManagerViewFxImpl} JavaFX HBox.
     */
    public SimManagerViewFxImpl() {
        final Button resume = new Button(RESUME);
        final Button pause = new Button(PAUSE);
        final Button oneSec = new Button(ONE_SEC);
        final Button oneMin = new Button(ONE_MIN);
        final Button oneHour = new Button(ONE_HOUR);

        resume.setOnAction(event -> this.simManager.resume());
        pause.setOnAction(event -> this.simManager.pause());
        oneSec.setOnAction(event -> this.simManager.setSimStepPeriod(Duration.ofSeconds(1)));
        oneMin.setOnAction(event -> this.simManager.setSimStepPeriod(Duration.ofMinutes(1)));
        oneHour.setOnAction(event -> this.simManager.setSimStepPeriod(Duration.ofHours(1)));

        lowerSection.getChildren().addAll(resume, pause, oneSec, oneMin, oneHour);
        upperSection.getChildren().addAll(datetime);
        this.getChildren().addAll(lowerSection, upperSection);
    }

    @Override
    public void setObserver(final SimManagerViewObserver simManager) {
        this.simManager = simManager;
    }

    @Override
    public void setDateTime(LocalDateTime simTime) {
        Platform.runLater(() -> this.datetime.setText(simTime.format(DateTimeFormatter.ISO_DATE_TIME)));
    }

}
