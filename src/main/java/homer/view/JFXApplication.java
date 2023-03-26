package homer.view;

import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.core.SimManagerImpl;
import homer.view.javafx.AddDevicesView;
import homer.view.sim.SimManagerViewImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JFXApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 500);
        VBox vBox = new VBox();
        Controller controller = new ControllerImpl();
        root.getChildren().addAll(vBox, new AddDevicesView(controller));

        final var simManager = new SimManagerImpl(controller);
        Platform.runLater(() -> {
            final var simView = new SimManagerViewImpl();
            simView.setObserver(simManager);
        });

        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();
    }

}
