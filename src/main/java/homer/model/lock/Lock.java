package homer.model.lock;

import homer.api.ToggleableDevice;
import homer.api.state.OnOffState;

/**
 * This interface models a lock.
 */
public interface Lock extends ToggleableDevice<OnOffState> {
}
