package homer.view.sim;

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
    private static final long ONE_X = 1L;
    private static final long FIFTY_X = 50L;
    private static final long FIVE_THOUSAND_X = 5000L;
    private static final String RATE_TEXT = "Time rate: ";
    private final HBox buttonsBox = new HBox();
    private final HBox clockBox = new HBox();
    private final HBox rateBox = new HBox();
    private final Text datetime = new Text();
    private final Text timerate = new Text();
    private SimManagerViewObserver simManager;

    /**
     * Creates a new {@link SimManagerViewFxImpl} JavaFX HBox.
     */
    public SimManagerViewFxImpl() {
        final Button resume = new Button(RESUME);
        final Button pause = new Button(PAUSE);
        final Button oneX = new Button(ONE_X + "x");
        final Button fiftyX = new Button(FIFTY_X + "x");
        final Button fiveThousandX = new Button(FIVE_THOUSAND_X + "x");

        resume.setOnAction(event -> this.simManager.resume());
        pause.setOnAction(event -> this.simManager.pause());
        oneX.setOnAction(event -> this.simManager.setTimeRate(ONE_X));
        fiftyX.setOnAction(event -> this.simManager.setTimeRate(FIFTY_X));
        fiveThousandX.setOnAction(event -> this.simManager.setTimeRate(FIVE_THOUSAND_X));

        buttonsBox.getChildren().addAll(resume, pause, oneX, fiftyX, fiveThousandX);
        clockBox.getChildren().addAll(datetime);
        rateBox.getChildren().addAll(new Text(RATE_TEXT), timerate);
        this.getChildren().addAll(buttonsBox, clockBox, rateBox);
    }

    @Override
    public void setObserver(final SimManagerViewObserver simManager) {
        this.simManager = simManager;
    }

    @Override
    public void setDateTime(final LocalDateTime simTime) {
        Platform.runLater(() -> this.datetime.setText(simTime.format(DateTimeFormatter.ISO_DATE_TIME)));
    }

    @Override
    public void setTimeRate(final long timeRate) {
        Platform.runLater(() -> this.timerate.setText(Long.toString(timeRate)));
    }

}
