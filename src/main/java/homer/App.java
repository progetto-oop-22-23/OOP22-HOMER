package homer;

import homer.view.JFXApplication;
/*UNCOMMENT TO SHOW ElectricalMeter View.
* WILL ADD THE VIEW TO JFXApplication ASAP
*/
//import homer.view.LaunchMeterView;

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
        /* JUST FOR TESTING PURPOSES. Uncomment for launching ElectricalMeter's view. */
        //Application.launch(LaunchMeterView.class, args);
        Application.launch(JFXApplication.class, args);

    }
}
