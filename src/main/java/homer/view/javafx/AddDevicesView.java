package homer.view.javafx;

import java.util.Map;
import java.util.stream.Collectors;

import homer.controller.Controller;
import homer.controller.command.createdevicecommand.CreateDeviceCommand;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

/**
 * View that allows the user to add devices.
 */
public final class AddDevicesView extends VBox {
    private final Controller controller;
    private final ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private final Button addDeviceButton = new Button("Add");
    private Map<String, CreateDeviceCommand> map;

    /**
     * The controller will both receive the commands and return the set
     * of valid commands on updates.
     * 
     * @param controller receives commands and returns valid commands.
     */
    public AddDevicesView(final Controller controller) {
        this.controller = controller;
        this.getChildren().addAll(choiceBox, addDeviceButton);
        this.update();
        this.addDeviceButton.setOnMouseClicked(e -> controller.receiveCommand(map.get(choiceBox.getValue())));
    }

    /**
     * Updates the view.
     */
    public void update() {
        this.choiceBox.getItems().clear();
        final var valid = this.controller.getDeviceManager().getValidCreateDeviceCommands();
        map = valid.stream()
                .collect(Collectors.toMap(CreateDeviceCommand::getStringRep, x -> x));
        this.choiceBox.getItems().addAll(map.keySet());
    }

}
