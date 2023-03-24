package homer.controller.protocol;

import java.util.List;

public interface SerializedState {

    List<String> getAdjustableValues();

    List<Boolean> getToggleableValues();

}
