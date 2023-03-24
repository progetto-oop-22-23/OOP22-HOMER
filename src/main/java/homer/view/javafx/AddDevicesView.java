package homer.view.javafx;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import homer.controller.Controller;
import homer.controller.UserSelectableDeviceType;
import homer.controller.command.ConnectDevice;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;


public final class AddDevicesView extends VBox {
    private Controller controller;
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    private Button addDeviceButton = new Button("Add");

    private final Map<String, UserSelectableDeviceType> stringReps = new HashMap<>(
        Map.ofEntries(
            new AbstractMap.SimpleEntry<>("C outlet", UserSelectableDeviceType.C_OUTLET),
            new AbstractMap.SimpleEntry<>("L outlet", UserSelectableDeviceType.L_OUTLET),
            new AbstractMap.SimpleEntry<>("Heating", UserSelectableDeviceType.HEATING),
            new AbstractMap.SimpleEntry<>("Air conditioning", UserSelectableDeviceType.AIR_CONDITIONING),
            new AbstractMap.SimpleEntry<>("Window", UserSelectableDeviceType.WINDOW)
        )
    ); 

    public AddDevicesView(final Controller controller) {
        this.controller = controller;
        this.getChildren().addAll(choiceBox, addDeviceButton);
        this.addDeviceButton.setOnMouseClicked(e -> 
            controller.receiveCommand(new ConnectDevice(null)));
    }

    public void update() {
        this.choiceBox.getItems().clear();
        final Set<UserSelectableDeviceType> userSelectableDeviceTypes = controller.getDeviceManager().getSelectableDeviceTypes();
        List<String> selectableStringReps =  stringReps.entrySet()
            .stream()
            .filter(x -> userSelectableDeviceTypes.contains(x.getValue()))
            .map(x -> x.getKey())
            .toList();
        this.choiceBox.getItems().addAll(selectableStringReps);
    }

}
