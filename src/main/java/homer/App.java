package homer;

import homer.view.javafx.JFXApplication;
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
        Application.launch(JFXApplication.class, args);
    }
}
