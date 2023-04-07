package homer.view.sim;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import homer.core.SimManagerViewObserver;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Implementation of {@link SimManagerView} using JavaFX.
 */
public final class SimManagerViewFxImpl extends VBox implements SimManagerView {

    private static final String RESUME = "Resume";
    private static final String PAUSE = "Pause";
    private static final long ONE = 1L;
    private static final long TWO_FIVE_HUNDRED = 2500L;
    private static final long FIVE_THOUSAND = 5000L;
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
        final Button normalRateBtn = new Button(ONE + "x");
        final Button mediumRateBtn = new Button(TWO_FIVE_HUNDRED + "x");
        final Button fastRateBtn = new Button(FIVE_THOUSAND + "x");

        resume.setOnAction(event -> this.simManager.resume());
        pause.setOnAction(event -> this.simManager.pause());
        normalRateBtn.setOnAction(event -> this.simManager.setTimeRate(ONE));
        mediumRateBtn.setOnAction(event -> this.simManager.setTimeRate(TWO_FIVE_HUNDRED));
        fastRateBtn.setOnAction(event -> this.simManager.setTimeRate(FIVE_THOUSAND));

        buttonsBox.getChildren().addAll(resume, pause, normalRateBtn, mediumRateBtn, fastRateBtn);
        clockBox.getChildren().addAll(datetime);
        rateBox.getChildren().addAll(new Text(RATE_TEXT), timerate);
        this.getChildren().addAll(buttonsBox, clockBox, rateBox);
    }

    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "This is a design choice to be able to"
            + " interface with the corresponding controller")
    public void setObserver(final SimManagerViewObserver simManager) {
        this.simManager = Objects.requireNonNull(simManager);
    }

    @Override
    public void setDateTime(final LocalDateTime simTime) {
        Platform.runLater(
                () -> this.datetime.setText(simTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
    }

    @Override
    public void setTimeRate(final long timeRate) {
        Platform.runLater(() -> this.timerate.setText(Long.toString(timeRate) + "x"));
    }

    @Override
    public void showError(final String title, final String text) {
        Platform.runLater(() -> {
            final var alert = new Alert(AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(title);
            alert.setContentText(text);
            alert.showAndWait();
        });
    }

}
