package homer.view.javafx.deviceview;

import homer.api.DeviceId;
import homer.api.DeviceState;
import homer.api.state.LockState;
import homer.controller.Controller;
import homer.controller.command.UpdateDeviceState;
import homer.view.javafx.JFXDeviceView;
import javafx.scene.control.Label;

/**
 * {@link homer.model.lock.Lock} view.
 */
public final class LockView extends JFXDeviceView {

    private final Label label = new Label("Lock");
    private final ToggleableView toggleableView;

    /**
     * 
     * @param deviceId
     * @param controller
     */
    public LockView(final DeviceId deviceId, final Controller controller) {
        toggleableView = new ToggleableView("LOCKED", "UNLOCKED",
                s -> controller.receiveCommand(new UpdateDeviceState(deviceId, new LockState(s))));
        this.getChildren().addAll(label, toggleableView);
    }

    @Override
    public void setState(final DeviceState state) {
        if (state instanceof LockState lockState) {
            toggleableView.setState(lockState.isOn());
        }
    }

}
