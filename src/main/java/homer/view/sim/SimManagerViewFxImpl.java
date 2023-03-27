package homer.view.sim;

import java.time.Duration;

import homer.core.SimManagerViewObserver;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Implementation of {@link SimManagerView} using JavaFX.
 */
public final class SimManagerViewFxImpl extends Stage implements SimManagerView {

    private static final String TITLE = "Simulation Manager";
    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";
    private static final String ONE_SEC = "1s";
    private static final String ONE_MIN = "1m";
    private static final String ONE_HOUR = "1h";
    // private static final double HEIGHT_SCALE = 0.2;
    // private static final double WIDTH_SCALE = 0.2;
    private SimManagerViewObserver simManager;

    /**
     * Creates a new {@link SimManagerViewFxImpl} and displays it.
     */
    public SimManagerViewFxImpl() {
        this.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        final Button resume = new Button(RESUME);
        final Button pause = new Button(PAUSE);
        final Button oneSec = new Button(ONE_SEC);
        final Button oneMin = new Button(ONE_MIN);
        final Button oneHour = new Button(ONE_HOUR);
        final HBox root = new HBox();
        root.getChildren().add(resume);
        root.getChildren().add(pause);
        root.getChildren().add(oneSec);
        root.getChildren().add(oneMin);
        root.getChildren().add(oneHour);
        // this.setScene(new Scene(root, 300, 300));
        this.setScene(new Scene(root));

        resume.setOnAction(event -> simManager.resume());
        pause.setOnAction(event -> simManager.pause());
        oneSec.setOnAction(event -> simManager.setSimStepPeriod(Duration.ofSeconds(1)));
        oneMin.setOnAction(event -> simManager.setSimStepPeriod(Duration.ofMinutes(1)));
        oneHour.setOnAction(event -> simManager.setSimStepPeriod(Duration.ofHours(1)));

        this.sizeToScene();
        this.setTitle(TITLE);
        this.show();
    }

    @Override
    public void setObserver(final SimManagerViewObserver simManager) {
        this.simManager = simManager;
    }

}
