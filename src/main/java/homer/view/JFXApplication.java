package homer.view;

import java.util.Set;

import homer.api.Controller;
import homer.api.DeviceId;
import homer.api.DeviceView;
import homer.view.javafx.AddDevicesView;
import homer.view.javafx.DisconnectDeviceButton;
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
        root.getChildren().add(vBox);
        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();
    }
    
}
