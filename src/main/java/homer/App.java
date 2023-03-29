package homer;

import homer.view.JFXApplication;
import javafx.application.Application;

/**
 * Main application class.
 */
public final class App {
    private App() {
        throw new UnsupportedOperationException("App should not be instantiated");
    }

    /**
     * Application entry point.
     * 
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        // JUST FOR TESTING PURPOSES. Uncomment for launching ElectricalMeter's view.
        // Application.launch(LaunchMeterView.class, args);
        Application.launch(JFXApplication.class, args);

    }
}
