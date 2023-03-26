package homer;

import homer.core.SimManager;
import homer.core.SimManagerImpl;
import homer.core.SimManagerViewObserver;
import homer.view.JFXApplication;
import homer.view.sim.SimManagerViewImpl;
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
        // TODO remove example

        final SimManagerViewObserver simManager = new SimManagerImpl(() -> {
            System.out.println("Hello World " + " " + System.currentTimeMillis());
        });
        final SimManager simAppSide = simManager; // use this if it needs to be passed to the loop.

        final var simView = new SimManagerViewImpl();
        simView.setObserver(simManager);

        Application.launch(JFXApplication.class, args);
    }
}
