package homer;

import homer.core.SimManager;
import homer.core.SimManagerImpl;
import homer.core.SimManagerViewObserver;
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
        // TODO remove example

        final SimManagerViewObserver simView = new SimManagerImpl(() -> {
            System.out.println("Hello World " + " " + System.currentTimeMillis());
        });
        final SimManager simAppSide = simView; // use this if it needs to be passed to the loop.

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        simView.pause();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        simView.resume();

        Application.launch(JFXApplication.class, args);
    }
}
