package homer.view;

import java.io.FileOutputStream;

import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.controller.history.AirQualityLogger;
import homer.controller.history.ConsumptionLogger;
import homer.controller.history.TemperatureLogger;
import homer.controller.scheduler.TemperatureSchedulerController;
import homer.view.graph.AirQualityGraphFx;
import homer.view.graph.ConsumptionGraphFx;
import homer.view.graph.TabViewBuilderFx;
import homer.view.graph.TemperatureGraphFx;
import homer.view.javafx.JFXDeviceViewer;
import homer.view.javafx.sensorsview.ElectricalMeterViewManager;
import homer.view.logger.Logger;
import homer.view.logger.LoggerImpl;
import homer.view.logger.TimeStampLogger;
import homer.view.scheduler.TemperatureSchedulerViewFx;
import homer.core.SimManagerImpl;
import homer.view.sim.SimManagerViewFxImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

        final Stage sensorStage = new Stage();
        final FXMLLoader dashboardLoader = new FXMLLoader(
                JFXApplication.class.getResource("/homer/view/javafx/sensorsview/SensorDashboardView.fxml"));
        final FXMLLoader meterLoader = new FXMLLoader(
                JFXApplication.class.getResource("/homer/view/javafx/sensorsview/ElectricalMeterView.fxml"));
        final BorderPane sensorRoot = dashboardLoader.load();

        // Load the second FXML file into the second tab
        final TabPane sensorTabPane = (TabPane) sensorRoot.getCenter();
        final ObservableList<Tab> tabs = sensorTabPane.getTabs();

        for (final Tab tab : tabs) {
            final String id = "#meterTab";
            if (tab.getId().equals(id)) {
                tab.setContent(meterLoader.load());
                break;
            }
        }

        final var root = new BorderPane();
        final Scene scene = new Scene(root, INITIAL_W, INITIAL_H);

        final Controller controller = new ControllerImpl();

        meterLoader.load();
        final ElectricalMeterViewManager meterViewManager = meterLoader.getController();
        meterViewManager.getMeter().setDeviceManger(controller.getDeviceManager());

        final var tempSchedulerView = new TemperatureSchedulerViewFx();
        final var tempScheduler = new TemperatureSchedulerController(tempSchedulerView, controller);
        tempSchedulerView.setScheduler(tempScheduler);

        final var simView = new SimManagerViewFxImpl();
        final var simManager = new SimManagerImpl(simView, controller);
        simView.setObserver(simManager);
        simManager.addObserver(tempScheduler);
        simManager.addObserver(meterViewManager.getMeter());

        final var viewManager = controller.getViewManager();
        final var dashboard = new JFXDeviceViewer(controller);
        viewManager.addView(dashboard);
        final Logger logger = new LoggerImpl(new FileOutputStream(".log"));
        viewManager.addView(new TimeStampLogger(logger, controller.getClock()));

        // CREATE MAIN WINDOW
        // add tabs:
        // - device viewer
        // (device widgets which include the remove button) with add device section
        // - scheduler
        // - graphs

        final var tempGraph = new TemperatureGraphFx();
        final var tempLogger = new TemperatureLogger(tempGraph, controller);
        simManager.addObserver(tempLogger);
        final var consumptionGraph = new ConsumptionGraphFx();
        // TODO add meter
        final var consumptionLogger = new ConsumptionLogger(consumptionGraph, controller.getClock(), null);
        // TODO uncomment once meter added
        // simManager.addObserver(consumptionLogger);
        final var airQualityGraph = new AirQualityGraphFx();
        final var airQualityLogger = new AirQualityLogger(airQualityGraph, controller);
        simManager.addObserver(airQualityLogger);

        final TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
        tabPane.setTabDragPolicy(TabDragPolicy.REORDER);

        final ScrollPane dashboardScrollPane = new ScrollPane(dashboard);
        dashboardScrollPane.setFitToHeight(true);
        dashboardScrollPane.setFitToWidth(true);
        // TODO separate the add devices from the device list, so that only the list is
        // scrollable
        final Tab devicesView = new Tab("DEVICES", dashboardScrollPane);
        final Tab schedulerTab = new Tab("SCHEDULER", tempSchedulerView);
        final Tab graphView = new Tab("GRAPHS",
                new TabViewBuilderFx()
                        .addTab("TEMPERATURE", tempGraph)
                        .addTab("ENERGY CONSUMPTION", consumptionGraph)
                        .addTab("AIR QUALITY", airQualityGraph)
                        .build());

        tabPane.getTabs().addAll(devicesView, schedulerTab, graphView);

        root.setCenter(tabPane);
        root.setBottom(simView);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();

        final Scene sensorScene = new Scene(sensorRoot, INITIAL_W, INITIAL_H);

        sensorStage.setTitle(TITLE);
        sensorStage.setScene(sensorScene);
        sensorStage.show();
    }

}
