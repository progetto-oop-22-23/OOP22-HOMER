package homer.view;

import homer.api.DeviceIdImpl;
import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.model.temperaturechangers.TemperatureChangerState;
import homer.view.javafx.AddDevicesView;
import homer.view.javafx.TemperatureChangerView;
import javafx.application.Application;
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
        final var state = new TemperatureChangerState().addCurrentIntensity(3.0).addMaxIntensity(10).addMinIntensity(1);
        Controller controller = new ControllerImpl();
        root.getChildren().addAll(vBox, new AddDevicesView(controller), new TemperatureChangerView(new DeviceIdImpl(), state, controller));
        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();
    }
    
}
