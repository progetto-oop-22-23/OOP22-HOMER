package homer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import homer.controller.impl.electricalmeter.ElectricalMeterImpl;
import homer.model.outlets.Outlet;
import homer.model.outlets.OutletFactory;
import homer.view.JFXApplication;
import homer.view.LaunchMeterView;
import homer.view.javafx.electricalmeter.scenebuilder.ElectricalMeterViewManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        //JUST FOR TESTING PURPOSES. Uncomment for launching ElectricalMeter's view.
        //Application.launch(LaunchMeterView.class, args);
        Application.launch(JFXApplication.class, args);
        
    }
}
