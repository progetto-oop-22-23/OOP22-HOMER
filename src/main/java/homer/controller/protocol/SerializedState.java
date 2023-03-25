package homer.controller.protocol;

import java.util.Map;

public interface SerializedState {

    Map<String, String> getAdjustableValues();

    Map<String, Boolean> getToggleableValues();

}
