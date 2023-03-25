package homer.controller.protocol;

import java.util.Map;

public class ExampleSerializer implements SerializedState {

    private final int value;

    public ExampleSerializer(int value) {
        this.value = value;
    }

    @Override
    public Map<String, String> getAdjustableValues() {
        return Map.of("ValueName", Integer.toString(value));
    }

    @Override
    public Map<String, Boolean> getToggleableValues() {
        return Map.of();
    }

}
