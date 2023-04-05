package homer.view.javafx;

import homer.api.DeviceView;
import javafx.scene.layout.VBox;

/**
 * Allows all {@link DeviceView} that are in the javaFX application to
 * implement javaFX methods.
 * at an interface level, and avoids casts in {@link JFXDeviceViewer}.
 * The tradeoff is that all device viewer must extend a VBox. There might be a
 * better way to do this, but it's an improvement compared to what we had
 * before.
 */
public abstract class JFXDeviceView extends VBox implements DeviceView {

}
