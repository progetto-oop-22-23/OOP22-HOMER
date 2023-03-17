package homer.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JFXApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 500);
        stage.setTitle("demo");
        stage.setScene(scene);
        stage.show();
    }
    
}
