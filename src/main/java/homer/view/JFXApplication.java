package homer.view;

import homer.controller.Controller;
import homer.controller.ControllerImpl;
import homer.model.environment.HomeEnvironment;
import homer.view.javafx.AddDevicesView;
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
        Controller controller = new ControllerImpl(null); // TODO add an environmente here
        root.getChildren().addAll(vBox, new AddDevicesView(controller));
        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();
    }
    
}
