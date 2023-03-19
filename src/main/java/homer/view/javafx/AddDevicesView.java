package homer.view.javafx;

import java.util.List;

import homer.api.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

public class AddDevicesView extends VBox {
    private Controller controller;
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private Button addDeviceButton = new Button();

    public AddDevicesView(final Controller controller) {
        this.getChildren().addAll(choiceBox, addDeviceButton);
        this.controller = controller;
        this.controller.getConnectableDeviceTypes().stream().forEach(x -> choiceBox.getItems().add(x));
        this.addDeviceButton.setOnMouseClicked(e -> controller.connectDevice(choiceBox.getValue()));
    }
}
