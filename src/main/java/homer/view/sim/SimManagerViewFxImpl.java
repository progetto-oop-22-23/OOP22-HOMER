package homer.view.sim;

import java.time.Duration;

import homer.core.SimManagerViewObserver;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Implementation of {@link SimManagerView} using JavaFX.
 */
public final class SimManagerViewFxImpl extends HBox implements SimManagerView {

    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";
    private static final String ONE_SEC = "1s";
    private static final String ONE_MIN = "1m";
    private static final String ONE_HOUR = "1h";
    // private static final double HEIGHT_SCALE = 0.2;
    // private static final double WIDTH_SCALE = 0.2;
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

        resume.setOnAction(event -> simManager.resume());
        pause.setOnAction(event -> simManager.pause());
        oneSec.setOnAction(event -> simManager.setSimStepPeriod(Duration.ofSeconds(1)));
        oneMin.setOnAction(event -> simManager.setSimStepPeriod(Duration.ofMinutes(1)));
        oneHour.setOnAction(event -> simManager.setSimStepPeriod(Duration.ofHours(1)));

        this.getChildren().addAll(resume, pause, oneSec, oneMin, oneHour);
    }

    @Override
    public void setObserver(final SimManagerViewObserver simManager) {
        this.simManager = simManager;
    }

}
