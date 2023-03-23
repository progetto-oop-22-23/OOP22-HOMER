package homer.view.javafx;

import homer.controller.Controller;
import homer.controller.command.ConnectDevice;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;


public final class AddDevicesView extends VBox {
    private Controller controller;
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private Button addDeviceButton = new Button("Add");

    public AddDevicesView(final Controller controller) {
        this.controller = controller;
        this.getChildren().addAll(choiceBox, addDeviceButton);
        this.update();
        this.addDeviceButton.setOnMouseClicked(e -> 
            controller.receiveCommand(new ConnectDevice(choiceBox.getValue()))
        );
    }

    public void update() {
        this.choiceBox.getItems().clear();
        // this.controller.getConnectableDeviceTypes().stream().forEach(x -> choiceBox.getItems().add(x));
    }

}
