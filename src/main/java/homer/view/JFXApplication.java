package homer.view;

import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.view.javafx.AddDevicesView;
import homer.view.logger.Logger;
import homer.view.logger.LoggerImpl;
import homer.core.SimManagerImpl;
import homer.view.sim.SimManagerViewFxImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TabPane.TabDragPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main javaFX application.
 */
public class JFXApplication extends Application {
    private static final long INITIAL_W = 300;
    private static final long INITIAL_H = 300;
    private static final String TITLE = "HOMER";

    @Override
    public final void start(final Stage stage) throws Exception {
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        final var root = new BorderPane();
        final Scene scene = new Scene(root, INITIAL_W, INITIAL_H);
        final Controller controller = new ControllerImpl();

        final var simManager = new SimManagerImpl(controller);
        final var simView = new SimManagerViewFxImpl();
        Platform.runLater(() -> {
            simView.setObserver(simManager);
        });

        final var viewManager = controller.getViewManager();
        final Logger logger = new LoggerImpl(null);
        viewManager.addView(logger);

        // CREATE MAIN WINDOW
        // add tabs:
        // - device viewer
        // (device widgets which include the remove button) with add device section
        // - scheduler
        // - graphs

        // TODO display the sim world time somewhere (top/bottom)

        final TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabPane.setTabDragPolicy(TabDragPolicy.REORDER);

        // TODO add dashboard
        final Tab devicesView = new Tab();
        devicesView.setText("DEVICES");
        devicesView.setContent(new AddDevicesView(controller));

        final Tab schedulerView = new Tab();
        schedulerView.setText("SCHEDULER");
        // schedulerView.setContent(null); // TODO

        final Tab graphView = new Tab();
        graphView.setText("GRAPHS");
        // graphView.setContent(null); // TODO

        tabPane.getTabs().addAll(devicesView, schedulerView, graphView);

        root.setCenter(tabPane);
        root.setBottom(simView);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

}
