package homer.view;

import homer.api.DeviceIdImpl;
import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.view.javafx.AddDevicesView;
import homer.view.javafx.JFXDeviceViewer;
import homer.view.javafx.TemperatureChangerView;
import homer.view.logger.Logger;
import homer.view.logger.LoggerImpl;
import homer.view.scheduler.TemperatureSchedulerViewFx;
import homer.core.SimManagerImpl;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.sim.SimManagerViewFxImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TabPane.TabDragPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        
        final var tempSchedulerView = new TemperatureSchedulerViewFx();
        final Controller controller = new ControllerImpl(tempSchedulerView);

        final var simView = new SimManagerViewFxImpl();
        final var simManager = new SimManagerImpl(simView, controller);
        simView.setObserver(simManager);

        final var viewManager = controller.getViewManager();
        final var dashboard = new JFXDeviceViewer(controller);
        viewManager.addView(dashboard);
        // final Logger logger = new LoggerImpl(null);
        // viewManager.addView(logger);

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

        final ScrollPane dashboardScrollPane = new ScrollPane(dashboard);
        dashboardScrollPane.setFitToHeight(true);
        dashboardScrollPane.setFitToWidth(true);
        // TODO separate the add devices from the device list, so that only the list is scrollable
        final Tab devicesView = new Tab("DEVICES", dashboardScrollPane); // TODO add dashboard
        final Tab schedulerTab = new Tab("SCHEDULER", tempSchedulerView); // TODO
        final Tab graphView = new Tab("GRAPHS", null); // TODO

        tabPane.getTabs().addAll(devicesView, schedulerTab, graphView);

        root.setCenter(tabPane);
        root.setBottom(simView);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

}
